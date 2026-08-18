package com.example.ui.components

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.data.models.Achievement
import com.example.data.models.AchievementType
import com.example.ui.theme.*

@Composable
fun AchievementsSection(
    achievements: List<Achievement>,
    onSelectAchievement: (Achievement) -> Unit,
    onAddAchievementClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedCategoryFilter by remember { mutableStateOf(AchievementType.ALL) }
    val categories = AchievementType.values().toList()

    val filteredAchievements = remember(selectedCategoryFilter, achievements) {
        if (selectedCategoryFilter == AchievementType.ALL) achievements
        else achievements.filter { it.category == selectedCategoryFilter }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SectionHeader(
                badgeText = "Credentials & Milestones",
                title = "Achievements & Recognition",
                subtitle = "Academic milestones, technical certifications, hackathons, and open-source contributions."
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Filter chips
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth().padding(bottom = 14.dp)
        ) {
            items(categories) { cat ->
                val isSelected = cat == selectedCategoryFilter
                FilterChip(
                    selected = isSelected,
                    onClick = { selectedCategoryFilter = cat },
                    label = { Text(cat.displayName, fontSize = 12.sp, fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MaterialTheme.colorScheme.primary,
                        selectedLabelColor = Color(0xFF00363D)
                    ),
                    shape = RoundedCornerShape(10.dp)
                )
            }
        }

        // Achievements list
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            filteredAchievements.forEach { achievement ->
                AchievementCard(
                    achievement = achievement,
                    onClick = { onSelectAchievement(achievement) }
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Add Milestone Action Card
        OutlinedButton(
            onClick = onAddAchievementClick,
            shape = RoundedCornerShape(12.dp),
            border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)),
            modifier = Modifier.fillMaxWidth().heightIn(min = 44.dp).testTag("add_achievement_btn")
        ) {
            Icon(imageVector = Icons.Default.AddCircleOutline, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("Record New Milestone / Achievement", fontWeight = FontWeight.SemiBold, fontSize = 13.sp, color = MaterialTheme.colorScheme.primary)
        }
    }
}

@Composable
private fun AchievementCard(
    achievement: Achievement,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surface,
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
        ),
        tonalElevation = 2.dp,
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .testTag("achievement_card_${achievement.id}")
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                        when (achievement.category) {
                            AchievementType.EDUCATION -> IndigoAccent.copy(alpha = 0.15f)
                            AchievementType.CERTIFICATION -> EmeraldTech.copy(alpha = 0.15f)
                            AchievementType.HACKATHON -> AmberTech.copy(alpha = 0.15f)
                            AchievementType.PROJECT_LAUNCH -> CyanAccent.copy(alpha = 0.15f)
                            else -> MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = getAchievementIcon(achievement.iconName),
                    contentDescription = achievement.title,
                    tint = when (achievement.category) {
                        AchievementType.EDUCATION -> IndigoAccent
                        AchievementType.CERTIFICATION -> EmeraldTech
                        AchievementType.HACKATHON -> AmberTech
                        AchievementType.PROJECT_LAUNCH -> CyanAccent
                        else -> MaterialTheme.colorScheme.primary
                    },
                    modifier = Modifier.size(22.dp)
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = achievement.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.weight(1f)
                    )
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
                    ) {
                        Text(
                            text = achievement.dateOrYear,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }

                Text(
                    text = achievement.issuerOrContext,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = CyanAccent,
                    modifier = Modifier.padding(top = 1.dp)
                )

                Text(
                    text = achievement.description,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    lineHeight = 17.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )

                if (achievement.tags.isNotEmpty()) {
                    Row(
                        modifier = Modifier.padding(top = 6.dp),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        achievement.tags.take(3).forEach { skill ->
                            Surface(
                                shape = RoundedCornerShape(4.dp),
                                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                            ) {
                                Text(
                                    text = skill,
                                    fontSize = 9.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AchievementDetailDialog(
    achievement: Achievement,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = MaterialTheme.colorScheme.background,
            border = androidx.compose.foundation.BorderStroke(1.2.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.45f)),
            modifier = Modifier.fillMaxWidth(0.95f).padding(vertical = 12.dp)
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = achievement.title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                        Text(text = "${achievement.issuerOrContext} • ${achievement.dateOrYear}", fontSize = 12.sp, color = MaterialTheme.colorScheme.primary)
                    }
                    IconButton(onClick = onDismiss) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
                    }
                }

                Divider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.25f), modifier = Modifier.padding(vertical = 10.dp))

                Text(text = "Category: ${achievement.category.displayName}", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = CyanAccent)
                Spacer(modifier = Modifier.height(6.dp))
                Text(text = achievement.description, fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurfaceVariant, lineHeight = 20.sp)

                if (achievement.tags.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(text = "Verified Competencies", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = EmeraldTech)
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        achievement.tags.forEach { s ->
                            TechTag(text = s, isHighlighted = true)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    if (achievement.linkUrl != null) {
                        OutlinedButton(
                            onClick = { openExternalUrl(context, achievement.linkUrl) },
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(imageVector = Icons.Default.OpenInNew, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Verify Credential", fontSize = 12.sp)
                        }
                    }

                    Button(
                        onClick = onDismiss,
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Close", color = Color(0xFF00363D), fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

private fun getAchievementIcon(name: String): ImageVector {
    return when (name) {
        "school" -> Icons.Default.School
        "verified" -> Icons.Default.Verified
        "rocket", "rocket_launch" -> Icons.Default.RocketLaunch
        "trophy", "emoji_events" -> Icons.Default.EmojiEvents
        "code" -> Icons.Default.Code
        "hub", "group_work" -> Icons.Default.Hub
        "shield" -> Icons.Default.Shield
        "eco" -> Icons.Default.Eco
        "android" -> Icons.Default.Android
        else -> Icons.Default.Star
    }
}
