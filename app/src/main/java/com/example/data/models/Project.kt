package com.example.data.models

data class Project(
    val id: String,
    val title: String,
    val slug: String,
    val category: ProjectCategory,
    val tagline: String,
    val description: String,
    val problemSolved: String,
    val solution: String,
    val technologies: List<String>,
    val imageResId: Int? = null,
    val status: String = "Production MVP",
    val featured: Boolean = false,
    val githubUrl: String = "https://github.com/yardie-del",
    val liveDemoUrl: String? = null,
    val apkUrl: String? = null,
    val date: String = "2025 - Present",
    val keyFeatures: List<String> = emptyList(),
    val architecture: String = "",
    val researchIdea: String = "",
    val challenges: List<ChallengeSolution> = emptyList(),
    val results: String = "",
    val lessonsLearned: String = "",
    val metrics: List<Pair<String, String>> = emptyList(),
    val futureRoadmap: List<String> = emptyList(),
    val simulatorType: SimulatorType? = null
)

data class ChallengeSolution(
    val challenge: String,
    val solution: String
)

enum class ProjectCategory(val displayName: String) {
    ALL("All"),
    WEB_APPS("Web Apps"),
    MOBILE_APPS("Mobile Apps"),
    BACKEND_API("Backend & APIs"),
    CYBERSECURITY("Cybersecurity"),
    STARTUP_PROJECTS("Startup Projects"),
    AGRICULTURE("Agriculture")
}

enum class SimulatorType {
    NYUMBA_LINK_PROPTECH,
    AFRICA_2050_STORYBOARD,
    AGRITECH_EXCHANGE,
    CYBERSECURITY_TERMINAL,
    ARCHCONNECT_PREVIEW
}

