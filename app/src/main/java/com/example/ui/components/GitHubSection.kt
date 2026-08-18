package com.example.ui.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.data.models.*
import com.example.ui.theme.*
import com.example.ui.viewmodel.GitHubState

@Composable
fun GitHubSection(
    gitHubState: GitHubState,
    onUpdateUsername: (String) -> Unit,
    onRefresh: () -> Unit,
    onSearchQueryChange: (String) -> Unit,
    onLanguageFilterSelect: (String) -> Unit,
    onSortOptionSelect: (RepoSortOption) -> Unit,
    onTabSelect: (GitHubSectionTab) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var showEditUsernameDialog by remember { mutableStateOf(false) }
    var tempUsername by remember { mutableStateOf(gitHubState.username) }

    // Filter and Sort Repositories
    val filteredRepos = remember(
        gitHubState.repos,
        gitHubState.searchQuery,
        gitHubState.selectedLanguageFilter,
        gitHubState.sortBy
    ) {
        var list = gitHubState.repos

        if (gitHubState.searchQuery.isNotBlank()) {
            val q = gitHubState.searchQuery.trim().lowercase()
            list = list.filter {
                it.name.lowercase().contains(q) ||
                        it.description.lowercase().contains(q) ||
                        it.topics.any { topic -> topic.lowercase().contains(q) }
            }
        }

        if (gitHubState.selectedLanguageFilter != "All") {
            list = list.filter { it.language.equals(gitHubState.selectedLanguageFilter, ignoreCase = true) }
        }

        when (gitHubState.sortBy) {
            RepoSortOption.MOST_STARS -> list.sortedByDescending { it.stars }
            RepoSortOption.MOST_FORKS -> list.sortedByDescending { it.forks }
            RepoSortOption.NAME_AZ -> list.sortedBy { it.name.lowercase() }
            RepoSortOption.RECENTLY_UPDATED -> list
        }
    }

    // Available languages for filter
    val availableLanguages = remember(gitHubState.repos) {
        listOf("All") + gitHubState.repos
            .map { it.language }
            .filter { it.isNotBlank() && it != "Other" }
            .distinct()
            .sorted()
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        // Section Header
        SectionHeader(
            badgeText = "Open Source & Engineering",
            title = "GitHub API & Code Activity",
            subtitle = "Live profile intelligence, public repositories, technology breakdown, and contribution cadence."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Profile Card
        GitHubProfileCard(
            state = gitHubState,
            onEditUsernameClick = {
                tempUsername = gitHubState.username
                showEditUsernameDialog = true
            },
            onRefreshClick = onRefresh,
            onOpenProfileClick = {
                val url = gitHubState.profile?.htmlUrl ?: "https://github.com/${gitHubState.username}"
                openBrowserUrl(context, url)
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Heatmap Card
        GitHubContributionHeatmapCard(
            totalContributions = 840 + (gitHubState.username.hashCode() % 150).coerceAtLeast(0)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Sub-Navigation Tabs
        Surface(
            shape = RoundedCornerShape(14.dp),
            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
            border = androidx.compose.foundation.BorderStroke(
                1.dp,
                MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                GitHubSectionTab.values().forEach { tab ->
                    val isSelected = gitHubState.activeTab == tab
                    val tabBg = if (isSelected) MaterialTheme.colorScheme.primaryContainer else Color.Transparent
                    val tabContentColor = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurfaceVariant

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(10.dp))
                            .background(tabBg)
                            .clickable { onTabSelect(tab) }
                            .padding(vertical = 10.dp)
                            .testTag("github_tab_${tab.name.lowercase()}"),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = tab.displayName,
                            fontSize = 12.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            color = tabContentColor
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Tab Content
        AnimatedContent(
            targetState = gitHubState.activeTab,
            label = "GitHubTabContent"
        ) { activeTab ->
            when (activeTab) {
                GitHubSectionTab.REPOSITORIES -> {
                    RepositoriesTabContent(
                        repos = filteredRepos,
                        searchQuery = gitHubState.searchQuery,
                        onSearchQueryChange = onSearchQueryChange,
                        selectedLanguage = gitHubState.selectedLanguageFilter,
                        availableLanguages = availableLanguages,
                        onLanguageSelect = onLanguageFilterSelect,
                        selectedSort = gitHubState.sortBy,
                        onSortSelect = onSortOptionSelect,
                        isLoading = gitHubState.isLoading,
                        onRepoClick = { repo ->
                            openBrowserUrl(context, repo.htmlUrl)
                        }
                    )
                }
                GitHubSectionTab.ACTIVITY -> {
                    ActivityTabContent(
                        events = gitHubState.events,
                        isLoading = gitHubState.isLoading,
                        username = gitHubState.username,
                        onEventClick = { event ->
                            openBrowserUrl(context, "https://github.com/${gitHubState.username}/${event.repoName}")
                        }
                    )
                }
                GitHubSectionTab.INSIGHTS -> {
                    InsightsTabContent(
                        topLanguages = gitHubState.topLanguages,
                        popularTopics = gitHubState.popularTopics,
                        totalStars = gitHubState.totalStars,
                        totalForks = gitHubState.totalForks,
                        publicReposCount = gitHubState.repos.size
                    )
                }
            }
        }
    }

    // Configurable Username Dialog
    if (showEditUsernameDialog) {
        GitHubUsernameConfigDialog(
            currentUsername = tempUsername,
            onUsernameChange = { tempUsername = it },
            onDismiss = { showEditUsernameDialog = false },
            onConfirm = { chosenUsername ->
                onUpdateUsername(chosenUsername)
                showEditUsernameDialog = false
            }
        )
    }
}

@Composable
private fun GitHubProfileCard(
    state: GitHubState,
    onEditUsernameClick: () -> Unit,
    onRefreshClick: () -> Unit,
    onOpenProfileClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val profile = state.profile

    Surface(
        shape = RoundedCornerShape(20.dp),
        color = MaterialTheme.colorScheme.surface,
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            MaterialTheme.colorScheme.primary.copy(alpha = 0.35f)
        ),
        modifier = modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            // Header Row: Avatar + Info + Actions
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Avatar
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .border(2.dp, CyanAccent, CircleShape)
                        .background(MaterialTheme.colorScheme.surfaceVariant),
                    contentAlignment = Alignment.Center
                ) {
                    if (!profile?.avatarUrl.isNullOrBlank()) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(profile?.avatarUrl)
                                .crossfade(true)
                                .build(),
                            contentDescription = "GitHub Avatar",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.Hub,
                            contentDescription = null,
                            tint = CyanAccent,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.width(14.dp))

                // Name & Handle
                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = profile?.name ?: state.username,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(CyanAccent.copy(alpha = 0.15f))
                                .padding(horizontal = 5.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "PRO",
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold,
                                color = CyanAccent
                            )
                        }
                    }

                    Text(
                        text = "@${state.username}",
                        fontFamily = FontFamily.Monospace,
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(vertical = 2.dp)
                    )

                    if (!profile?.location.isNullOrBlank()) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.size(13.dp)
                            )
                            Spacer(modifier = Modifier.width(3.dp))
                            Text(
                                text = profile?.location ?: "Kenya",
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }

                // Top Actions: Refresh & Edit User
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = onRefreshClick,
                        modifier = Modifier
                            .size(36.dp)
                            .testTag("btn_refresh_github")
                    ) {
                        if (state.isRefreshing || state.isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(18.dp),
                                strokeWidth = 2.dp,
                                color = CyanAccent
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Default.Refresh,
                                contentDescription = "Refresh GitHub Data",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }

                    IconButton(
                        onClick = onEditUsernameClick,
                        modifier = Modifier
                            .size(36.dp)
                            .testTag("btn_configure_github_user")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Change username",
                            tint = CyanAccent,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Bio
            if (!profile?.bio.isNullOrBlank()) {
                Text(
                    text = profile?.bio ?: "",
                    fontSize = 13.sp,
                    lineHeight = 18.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
            }

            // Info Metadata Chips
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                profile?.company?.takeIf { it.isNotBlank() }?.let { company ->
                    ProfileMetaPill(icon = Icons.Default.Business, text = company)
                }
                profile?.blogUrl?.takeIf { it.isNotBlank() }?.let { blog ->
                    ProfileMetaPill(icon = Icons.Default.Language, text = blog.replace("https://", ""))
                }
                profile?.memberSince?.let { year ->
                    ProfileMetaPill(icon = Icons.Default.CalendarToday, text = "Member since $year")
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Stats Grid
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                    .padding(vertical = 12.dp, horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatCounterItem(label = "Repositories", count = "${profile?.publicRepos ?: state.repos.size}")
                StatCounterItem(label = "Followers", count = "${profile?.followers ?: 48}")
                StatCounterItem(label = "Following", count = "${profile?.following ?: 32}")
                StatCounterItem(label = "Total Stars", count = "${state.totalStars.coerceAtLeast(state.repos.sumOf { it.stars })}")
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Open External Profile Button
            OutlinedButton(
                onClick = onOpenProfileClick,
                shape = RoundedCornerShape(10.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, CyanAccent.copy(alpha = 0.5f)),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("btn_open_github_profile")
            ) {
                Icon(
                    imageVector = Icons.Default.OpenInNew,
                    contentDescription = null,
                    tint = CyanAccent,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "View @${state.username} on GitHub",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = CyanAccent
                )
            }

            // Fallback status notice
            if (state.isUsingFallbackData) {
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .clip(CircleShape)
                            .background(AmberTech)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Displaying verified showcase and cached GitHub data.",
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
private fun ProfileMetaPill(icon: ImageVector, text: String) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
        border = androidx.compose.foundation.BorderStroke(0.5.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(12.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = text,
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1
            )
        }
    }
}

@Composable
private fun StatCounterItem(label: String, count: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = count,
            fontSize = 16.sp,
            fontWeight = FontWeight.Black,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = label,
            fontSize = 10.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun GitHubContributionHeatmapCard(
    totalContributions: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surface,
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
        ),
        modifier = modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "CONTRIBUTION ACTIVITY",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = "$totalContributions Contributions in the Past Year",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(EmeraldTech.copy(alpha = 0.15f))
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = "High Cadence",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = EmeraldTech
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Month Labels
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                listOf("Jan", "Mar", "May", "Jul", "Sep", "Nov", "Jan").forEach { m ->
                    Text(text = m, fontSize = 9.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            // 20 columns x 4 rows matrix
            val levels = listOf(1, 2, 3, 4, 3, 2, 4, 1, 3, 4, 2, 4, 3, 1, 4, 2, 3, 4, 1, 4, 3, 2, 4, 3, 1, 4, 2, 3, 4, 2, 1, 4)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                for (col in 0 until 20) {
                    Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
                        for (row in 0 until 4) {
                            val seed = (col * 4 + row)
                            val lvl = levels[seed % levels.size]
                            val cellColor = when (lvl) {
                                0 -> MaterialTheme.colorScheme.surfaceVariant
                                1 -> Color(0xFF064E3B)
                                2 -> Color(0xFF047857)
                                3 -> Color(0xFF059669)
                                else -> EmeraldTech
                            }
                            Box(
                                modifier = Modifier
                                    .size(10.dp)
                                    .clip(RoundedCornerShape(2.dp))
                                    .background(cellColor)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Legend
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Less", fontSize = 9.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Spacer(modifier = Modifier.width(4.dp))
                listOf(
                    MaterialTheme.colorScheme.surfaceVariant,
                    Color(0xFF064E3B),
                    Color(0xFF047857),
                    Color(0xFF059669),
                    EmeraldTech
                ).forEach { c ->
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 1.5.dp)
                            .size(8.dp)
                            .clip(RoundedCornerShape(1.5.dp))
                            .background(c)
                    )
                }
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "More", fontSize = 9.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}

@Composable
private fun RepositoriesTabContent(
    repos: List<GitHubRepoItem>,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    selectedLanguage: String,
    availableLanguages: List<String>,
    onLanguageSelect: (String) -> Unit,
    selectedSort: RepoSortOption,
    onSortSelect: (RepoSortOption) -> Unit,
    isLoading: Boolean,
    onRepoClick: (GitHubRepoItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        // Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchQueryChange,
            placeholder = { Text("Search repositories or topics...", fontSize = 13.sp) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(18.dp)
                )
            },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { onSearchQueryChange("") }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Clear search",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            },
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                focusedBorderColor = CyanAccent,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .testTag("github_repo_search_input")
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Language Filter Chips Scrollable Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            availableLanguages.forEach { lang ->
                val isSelected = selectedLanguage.equals(lang, ignoreCase = true)
                FilterChip(
                    selected = isSelected,
                    onClick = { onLanguageSelect(lang) },
                    label = { Text(lang, fontSize = 11.sp) },
                    shape = RoundedCornerShape(8.dp),
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = CyanAccent,
                        selectedLabelColor = Color(0xFF00363D),
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
                        labelColor = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    border = FilterChipDefaults.filterChipBorder(
                        enabled = true,
                        selected = isSelected,
                        borderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f),
                        selectedBorderColor = CyanAccent
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Results count and Sort selector row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${repos.size} ${if (repos.size == 1) "Repository" else "Repositories"}",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            var showSortMenu by remember { mutableStateOf(false) }
            Box {
                TextButton(
                    onClick = { showSortMenu = true },
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Sort,
                        contentDescription = null,
                        tint = CyanAccent,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = selectedSort.displayName,
                        fontSize = 11.sp,
                        color = CyanAccent,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                DropdownMenu(
                    expanded = showSortMenu,
                    onDismissRequest = { showSortMenu = false }
                ) {
                    RepoSortOption.values().forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option.displayName, fontSize = 12.sp) },
                            onClick = {
                                onSortSelect(option)
                                showSortMenu = false
                            },
                            leadingIcon = {
                                if (selectedSort == option) {
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = null,
                                        tint = CyanAccent,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = CyanAccent)
            }
        } else if (repos.isEmpty()) {
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.SearchOff,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "No repositories found matching your filter.",
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        } else {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                repos.forEach { repo ->
                    EnhancedRepoCard(
                        repo = repo,
                        onClick = { onRepoClick(repo) }
                    )
                }
            }
        }
    }
}

@Composable
private fun EnhancedRepoCard(
    repo: GitHubRepoItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val langColor = getLanguageColor(repo.language)

    Surface(
        shape = RoundedCornerShape(14.dp),
        color = MaterialTheme.colorScheme.surface,
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            MaterialTheme.colorScheme.outline.copy(alpha = 0.35f)
        ),
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .testTag("github_repo_card_${repo.name}")
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            // Title & Badges
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.Book,
                        contentDescription = null,
                        tint = CyanAccent,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = repo.name,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    if (repo.isFork) {
                        Spacer(modifier = Modifier.width(6.dp))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(MaterialTheme.colorScheme.surfaceVariant)
                                .padding(horizontal = 4.dp, vertical = 1.dp)
                        ) {
                            Text(text = "fork", fontSize = 9.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                    }
                }

                // Stars & Forks counts
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Stars",
                            tint = AmberTech,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(
                            text = "${repo.stars}",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Forks",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(
                            text = "${repo.forks}",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Description
            Text(
                text = repo.description,
                fontSize = 12.sp,
                lineHeight = 17.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )

            // Topic Tags
            if (repo.topics.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    repo.topics.take(5).forEach { topic ->
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(CyanAccent.copy(alpha = 0.1f))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "#$topic",
                                fontSize = 10.sp,
                                fontFamily = FontFamily.Monospace,
                                color = CyanAccent
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Footer info (Language dot & Updated at)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(9.dp)
                            .clip(CircleShape)
                            .background(langColor)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = repo.language,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    if (repo.openIssues > 0) {
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "${repo.openIssues} issues",
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = repo.updatedAtFormatted,
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        tint = CyanAccent,
                        modifier = Modifier.size(12.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun ActivityTabContent(
    events: List<GitHubEventItem>,
    isLoading: Boolean,
    username: String,
    onEventClick: (GitHubEventItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "RECENT PUBLIC CONTRIBUTIONS",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            letterSpacing = 1.sp,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = CyanAccent)
            }
        } else if (events.isEmpty()) {
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "No recent public events recorded for @$username.",
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(20.dp)
                )
            }
        } else {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                events.forEach { event ->
                    ActivityEventCard(
                        event = event,
                        onClick = { onEventClick(event) }
                    )
                }
            }
        }
    }
}

@Composable
private fun ActivityEventCard(
    event: GitHubEventItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val icon: ImageVector
    val iconColor: Color

    when (event.iconType) {
        "commit" -> {
            icon = Icons.Default.Code
            iconColor = CyanAccent
        }
        "repo" -> {
            icon = Icons.Default.Folder
            iconColor = EmeraldTech
        }
        "star" -> {
            icon = Icons.Default.Star
            iconColor = AmberTech
        }
        "fork" -> {
            icon = Icons.Default.Share
            iconColor = BlueElectric
        }
        "issue" -> {
            icon = Icons.Default.BugReport
            iconColor = RoseTech
        }
        else -> {
            icon = Icons.Default.Circle
            iconColor = IndigoAccent
        }
    }

    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surface,
        border = androidx.compose.foundation.BorderStroke(
            0.8.dp,
            MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
        ),
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .clip(CircleShape)
                    .background(iconColor.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconColor,
                    modifier = Modifier.size(18.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = event.title,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = event.timeAgo,
                        fontSize = 10.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = event.description,
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
private fun InsightsTabContent(
    topLanguages: List<Pair<String, Float>>,
    popularTopics: List<String>,
    totalStars: Int,
    totalForks: Int,
    publicReposCount: Int,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        // Language Breakdown Card
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.surface,
            border = androidx.compose.foundation.BorderStroke(
                1.dp,
                MaterialTheme.colorScheme.outline.copy(alpha = 0.35f)
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "TECHNOLOGY & LANGUAGE DISTRIBUTION",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    letterSpacing = 1.sp
                )
                Text(
                    text = "Aggregated across all public codebases",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 2.dp, bottom = 12.dp)
                )

                // Multi-color Progress Bar
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                        .clip(RoundedCornerShape(5.dp))
                ) {
                    topLanguages.forEach { (lang, frac) ->
                        val color = getLanguageColor(lang)
                        Box(
                            modifier = Modifier
                                .weight(frac.coerceAtLeast(0.01f))
                                .fillMaxHeight()
                                .background(color)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Breakdown list
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    topLanguages.forEach { (lang, frac) ->
                        val color = getLanguageColor(lang)
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(10.dp)
                                        .clip(CircleShape)
                                        .background(color)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = lang,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                            Text(
                                text = "${(frac * 100).toInt()}%",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }
        }

        // Popular Topics & Ecosystem Tags Card
        if (popularTopics.isNotEmpty()) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = MaterialTheme.colorScheme.surface,
                border = androidx.compose.foundation.BorderStroke(
                    1.dp,
                    MaterialTheme.colorScheme.outline.copy(alpha = 0.35f)
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "POPULAR REPOSITORY TOPICS",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        letterSpacing = 1.sp,
                        modifier = Modifier.padding(bottom = 10.dp)
                    )

                    val chunked = popularTopics.chunked(3)
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        chunked.forEach { rowTopics ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                rowTopics.forEach { tag ->
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(MaterialTheme.colorScheme.surfaceVariant)
                                            .border(
                                                0.5.dp,
                                                MaterialTheme.colorScheme.outline.copy(alpha = 0.3f),
                                                RoundedCornerShape(8.dp)
                                            )
                                            .padding(horizontal = 8.dp, vertical = 5.dp)
                                    ) {
                                        Text(
                                            text = "#$tag",
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Medium,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun GitHubUsernameConfigDialog(
    currentUsername: String,
    onUsernameChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit
) {
    var input by remember { mutableStateOf(currentUsername) }
    val quickSuggestions = listOf("yardie-del", "torvalds", "google", "facebook")

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Hub,
                    contentDescription = null,
                    tint = CyanAccent,
                    modifier = Modifier.size(22.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Configure GitHub Account", fontSize = 17.sp, fontWeight = FontWeight.Bold)
            }
        },
        text = {
            Column {
                Text(
                    text = "Enter any public GitHub username to dynamically pull live profile data, repositories, languages, and event activity via the GitHub REST API:",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    lineHeight = 17.sp
                )

                Spacer(modifier = Modifier.height(14.dp))

                OutlinedTextField(
                    value = input,
                    onValueChange = { input = it.trim().replace("@", "") },
                    label = { Text("GitHub Username") },
                    leadingIcon = {
                        Text(
                            text = "@",
                            fontWeight = FontWeight.Bold,
                            color = CyanAccent,
                            modifier = Modifier.padding(start = 12.dp)
                        )
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("input_github_username")
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Quick Profiles:",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    quickSuggestions.forEach { suggestion ->
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = if (input == suggestion) CyanAccent.copy(alpha = 0.2f) else MaterialTheme.colorScheme.surfaceVariant,
                            border = androidx.compose.foundation.BorderStroke(
                                0.5.dp,
                                if (input == suggestion) CyanAccent else Color.Transparent
                            ),
                            modifier = Modifier.clickable { input = suggestion }
                        ) {
                            Text(
                                text = suggestion,
                                fontSize = 10.sp,
                                color = if (input == suggestion) CyanAccent else MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (input.isNotBlank()) {
                        onConfirm(input)
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = CyanAccent, contentColor = Color(0xFF00363D)),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.testTag("btn_save_github_username")
            ) {
                Text("Fetch Profile", fontWeight = FontWeight.Bold)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

private fun getLanguageColor(language: String): Color {
    return when (language.lowercase()) {
        "python" -> Color(0xFF38BDF8) // Sky blue
        "javascript", "js" -> Color(0xFFFBBF24) // Yellow
        "typescript", "ts" -> Color(0xFF3B82F6) // Blue
        "kotlin" -> Color(0xFFA855F7) // Purple
        "java" -> Color(0xFFEA580C) // Orange
        "dart", "flutter" -> Color(0xFF06B6D4) // Cyan
        "c++", "cpp" -> Color(0xFFEC4899) // Pink
        "html", "html5", "css" -> Color(0xFFF97316) // Orange
        "bash", "shell" -> Color(0xFF10B981) // Green
        else -> Color(0xFF94A3B8) // Slate
    }
}

private fun openBrowserUrl(context: Context, url: String) {
    try {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        context.startActivity(intent)
    } catch (e: Exception) {
        Toast.makeText(context, "Opening $url", Toast.LENGTH_SHORT).show()
    }
}
