package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.components.*
import com.example.ui.theme.MosesPortfolioTheme
import com.example.ui.viewmodel.NavigationSection
import com.example.ui.viewmodel.PortfolioViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: PortfolioViewModel = viewModel()
            val isDarkTheme by viewModel.isDarkTheme.collectAsState()

            MosesPortfolioTheme(darkTheme = isDarkTheme) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MosesPortfolioScreen(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun MosesPortfolioScreen(
    viewModel: PortfolioViewModel,
    modifier: Modifier = Modifier
) {
    val isDarkTheme by viewModel.isDarkTheme.collectAsState()
    val activeSection by viewModel.activeSection.collectAsState()
    val projectFilter by viewModel.selectedProjectFilter.collectAsState()
    val skillCategory by viewModel.selectedSkillCategory.collectAsState()
    val caseStudyProject by viewModel.selectedProjectForCaseStudy.collectAsState()
    val simulatorProject by viewModel.selectedProjectForSimulator.collectAsState()
    val selectedSkillDetail by viewModel.selectedSkillForDetail.collectAsState()
    val githubState by viewModel.githubState.collectAsState()
    val contactForm by viewModel.contactForm.collectAsState()
    val projectRequestForm by viewModel.projectRequestForm.collectAsState()
    val achievements by viewModel.achievements.collectAsState()
    val showCvDialog by viewModel.showCvDialog.collectAsState()
    val selectedCyberLab by viewModel.selectedCyberLab.collectAsState()
    val selectedAchievement by viewModel.selectedAchievement.collectAsState()
    val savedInquiries by viewModel.savedInquiries.collectAsState()
    val showInquiriesInbox by viewModel.showInquiriesInbox.collectAsState()
    val showNewMilestoneDialog by viewModel.showNewMilestoneDialog.collectAsState()

    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    // Map section to item index in LazyColumn
    val sectionIndexMap = mapOf(
        NavigationSection.HERO to 0,
        NavigationSection.ABOUT to 1,
        NavigationSection.SKILLS to 2,
        NavigationSection.PROJECTS to 3,
        NavigationSection.CASE_STUDIES to 4,
        NavigationSection.HOW_I_BUILD to 5,
        NavigationSection.GITHUB to 6,
        NavigationSection.CYBERSECURITY to 7,
        NavigationSection.ACHIEVEMENTS to 8,
        NavigationSection.BUILD_WITH_ME to 9,
        NavigationSection.CONTACT to 10
    )

    fun navigateToSection(section: NavigationSection) {
        viewModel.setActiveSection(section)
        val targetIndex = sectionIndexMap[section] ?: 0
        coroutineScope.launch {
            listState.animateScrollToItem(targetIndex)
        }
    }

    Scaffold(
        topBar = {
            PortfolioTopBar(
                activeSection = activeSection,
                onSelectSection = { navigateToSection(it) },
                isDarkTheme = isDarkTheme,
                onToggleTheme = { viewModel.toggleTheme() }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navigateToSection(NavigationSection.BUILD_WITH_ME) },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color(0xFF00363D),
                modifier = Modifier.testTag("fab_contact")
            ) {
                Icon(
                    imageVector = Icons.Default.Handshake,
                    contentDescription = "Build With Moses"
                )
            }
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .drawBehind {
                    if (isDarkTheme) {
                        // Ambient top-left cyan glow
                        drawCircle(
                            brush = Brush.radialGradient(
                                colors = listOf(Color(0x28164E63), Color.Transparent),
                                center = Offset(size.width * 0.15f, size.height * 0.12f),
                                radius = size.width * 0.9f
                            ),
                            radius = size.width * 0.9f,
                            center = Offset(size.width * 0.15f, size.height * 0.12f)
                        )
                        // Ambient bottom-right indigo glow
                        drawCircle(
                            brush = Brush.radialGradient(
                                colors = listOf(Color(0x1F312E81), Color.Transparent),
                                center = Offset(size.width * 0.85f, size.height * 0.75f),
                                radius = size.width * 0.85f
                            ),
                            radius = size.width * 0.85f,
                            center = Offset(size.width * 0.85f, size.height * 0.75f)
                        )
                    }
                }
                .padding(innerPadding)
        ) {
            LazyColumn(
                state = listState,
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .testTag("portfolio_scrollable_feed")
            ) {
                // 0. Hero Section
                item {
                    HeroSection(
                        onNavigateSection = { navigateToSection(it) },
                        onOpenContact = { navigateToSection(NavigationSection.CONTACT) },
                        onOpenCv = { viewModel.setShowCvDialog(true) },
                        onHireMe = { navigateToSection(NavigationSection.BUILD_WITH_ME) }
                    )
                }

                // 1. About Section
                item {
                    AboutSection()
                }

                // 2. Skills Section
                item {
                    SkillsSection(
                        selectedCategory = skillCategory,
                        onSelectCategory = { viewModel.setSkillCategory(it) },
                        onSkillClick = { viewModel.openSkillDetail(it) }
                    )
                }

                // 3. Projects Section
                item {
                    ProjectsSection(
                        selectedCategory = projectFilter,
                        onSelectCategory = { viewModel.setProjectFilter(it) },
                        onOpenCaseStudy = { viewModel.openCaseStudy(it) },
                        onOpenSimulator = { viewModel.openSimulator(it) }
                    )
                }

                // 4. Case Studies Section
                item {
                    CaseStudiesSection(
                        onOpenCaseStudy = { viewModel.openCaseStudy(it) },
                        onOpenSimulator = { viewModel.openSimulator(it) }
                    )
                }

                // 5. How I Build (Engineering Methodology)
                item {
                    HowIBuildSection()
                }

                // 6. GitHub Section (yardie-del live sync)
                item {
                    GitHubSection(
                        gitHubState = githubState,
                        onUpdateUsername = { viewModel.updateGitHubUsername(it) },
                        onRefresh = { viewModel.refreshGitHub() },
                        onSearchQueryChange = { viewModel.setGitHubSearchQuery(it) },
                        onLanguageFilterSelect = { viewModel.setGitHubLanguageFilter(it) },
                        onSortOptionSelect = { viewModel.setGitHubSortBy(it) },
                        onTabSelect = { viewModel.setGitHubTab(it) }
                    )
                }

                // 7. Cybersecurity Section & Defensive Labs
                item {
                    CybersecuritySection(
                        onOpenLabDetail = { viewModel.openCyberLab(it) }
                    )
                }

                // 8. Achievements Section
                item {
                    AchievementsSection(
                        achievements = achievements,
                        onSelectAchievement = { viewModel.openAchievement(it) },
                        onAddAchievementClick = { viewModel.setShowNewMilestoneDialog(true) }
                    )
                }

                // 9. Build With Me (Client Acquisition Platform)
                item {
                    BuildWithMeSection(
                        formState = projectRequestForm,
                        onNameChange = { viewModel.updateRequestName(it) },
                        onEmailChange = { viewModel.updateRequestEmail(it) },
                        onPhoneChange = { viewModel.updateRequestPhone(it) },
                        onProjectTypeChange = { viewModel.updateRequestProjectType(it) },
                        onBudgetChange = { viewModel.updateRequestBudget(it) },
                        onDescriptionChange = { viewModel.updateRequestDescription(it) },
                        onTimelineChange = { viewModel.updateRequestTimeline(it) },
                        onSubmit = { viewModel.submitProjectRequest() },
                        onReset = { viewModel.resetProjectRequest() },
                        savedInquiriesCount = savedInquiries.size,
                        onOpenInquiriesInbox = { viewModel.setShowInquiriesInbox(true) }
                    )
                }

                // 10. Contact Section
                item {
                    ContactSection(
                        formState = contactForm,
                        onNameChange = { viewModel.updateContactName(it) },
                        onEmailChange = { viewModel.updateContactEmail(it) },
                        onSubjectChange = { viewModel.updateContactSubject(it) },
                        onMessageChange = { viewModel.updateContactMessage(it) },
                        onProjectTypeChange = { viewModel.updateContactProjectType(it) },
                        onBudgetChange = { viewModel.updateContactBudget(it) },
                        onSubmit = { viewModel.submitContactForm() },
                        onReset = { viewModel.resetContactForm() },
                        savedInquiriesCount = savedInquiries.size,
                        onOpenInquiriesInbox = { viewModel.setShowInquiriesInbox(true) }
                    )
                }

                // 11. Testimonials & Professional References
                item {
                    TestimonialsSection(
                        onRequestReference = { navigateToSection(NavigationSection.CONTACT) }
                    )
                }

                // 12. Footer
                item {
                    PortfolioFooter(
                        onSelectSection = { navigateToSection(it) }
                    )
                }
            }
        }
    }

    // Modal Dialogs
    caseStudyProject?.let { project ->
        ProjectCaseStudyDialog(
            project = project,
            onDismiss = { viewModel.closeCaseStudy() },
            onOpenSimulator = { viewModel.openSimulator(it) }
        )
    }

    simulatorProject?.let { project ->
        ProjectSimulatorDialog(
            project = project,
            onDismiss = { viewModel.closeSimulator() }
        )
    }

    selectedSkillDetail?.let { skill ->
        SkillDetailDialog(
            skill = skill,
            onDismiss = { viewModel.closeSkillDetail() }
        )
    }

    if (showCvDialog) {
        CvViewerDialog(
            onDismiss = { viewModel.setShowCvDialog(false) }
        )
    }

    selectedCyberLab?.let { lab ->
        CyberLabDetailDialog(
            lab = lab,
            onDismiss = { viewModel.closeCyberLab() }
        )
    }

    selectedAchievement?.let { achievement ->
        AchievementDetailDialog(
            achievement = achievement,
            onDismiss = { viewModel.closeAchievement() }
        )
    }

    if (showInquiriesInbox) {
        InquiriesInboxDialog(
            inquiries = savedInquiries,
            onDeleteInquiry = { viewModel.deleteInquiry(it) },
            onDismiss = { viewModel.setShowInquiriesInbox(false) }
        )
    }

    if (showNewMilestoneDialog) {
        NewMilestoneDialog(
            onDismiss = { viewModel.setShowNewMilestoneDialog(false) },
            onSave = { year, title, subtitle, desc, tags ->
                viewModel.addMilestone(year, title, subtitle, desc, tags)
            }
        )
    }
}
