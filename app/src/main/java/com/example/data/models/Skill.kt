package com.example.data.models

data class Skill(
    val name: String,
    val category: SkillCategory,
    val proficiency: Int, // 0 - 100
    val levelLabel: String = "Project Experience", // "Project Experience", "Familiar", "Learning"
    val experienceNote: String,
    val projectEvidence: String = "",
    val iconTag: String = "code"
)

enum class SkillCategory(val title: String) {
    FRONTEND("Frontend"),
    BACKEND("Backend & APIs"),
    MOBILE("Mobile"),
    TOOLS("Tools & DevOps"),
    OTHER_FOUNDATIONS("Foundations & Security")
}

data class EngineeringStep(
    val stepNumber: Int,
    val title: String,
    val subtitle: String,
    val description: String,
    val keyPractices: List<String>,
    val iconName: String
)

data class CyberSecurityTopic(
    val id: String,
    val title: String,
    val domain: String,
    val description: String,
    val practices: List<String>,
    val iconName: String
)

data class CyberSecurityLab(
    val id: String,
    val title: String,
    val objective: String,
    val conceptExplained: String,
    val defenseStrategy: String,
    val implementationExample: String,
    val status: String = "Educational Lab Sandbox"
)

data class Achievement(
    val id: String,
    val title: String,
    val category: AchievementType,
    val issuerOrContext: String,
    val dateOrYear: String,
    val description: String,
    val linkUrl: String? = null,
    val tags: List<String> = emptyList(),
    val iconName: String = "trophy"
)

enum class AchievementType(val displayName: String) {
    ALL("All"),
    EDUCATION("Education"),
    CERTIFICATION("Certifications"),
    HACKATHON("Hackathons & Contests"),
    OPEN_SOURCE("Open Source"),
    PROJECT_LAUNCH("Project Launches"),
    CLIENT_WORK("Client Deliveries")
}

data class Testimonial(
    val id: String,
    val authorName: String,
    val authorRole: String,
    val organization: String,
    val quote: String,
    val date: String,
    val projectContext: String
)

data class Milestone(
    val id: String,
    val year: String,
    val title: String,
    val subtitle: String,
    val description: String,
    val tags: List<String>,
    val isKeyAchievement: Boolean = false
)

data class ServiceOffering(
    val id: String,
    val title: String,
    val shortDesc: String,
    val fullDesc: String,
    val deliverables: List<String>,
    val techStack: List<String>,
    val iconName: String
)

data class GitHubRepo(
    val name: String,
    val description: String,
    val language: String,
    val stars: Int,
    val forks: Int,
    val isFeatured: Boolean = false,
    val updatedAgo: String = "Active",
    val tags: List<String> = emptyList()
)

