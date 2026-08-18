package com.example.data.models

data class GitHubUserProfile(
    val username: String,
    val name: String,
    val avatarUrl: String?,
    val bio: String,
    val company: String?,
    val location: String?,
    val blogUrl: String?,
    val twitterHandle: String?,
    val publicRepos: Int,
    val followers: Int,
    val following: Int,
    val memberSince: String,
    val htmlUrl: String
)

data class GitHubRepoItem(
    val id: Long,
    val name: String,
    val fullName: String,
    val description: String,
    val language: String,
    val stars: Int,
    val forks: Int,
    val openIssues: Int,
    val htmlUrl: String,
    val updatedAtFormatted: String,
    val topics: List<String>,
    val isFork: Boolean
)

data class GitHubEventItem(
    val id: String,
    val type: String,
    val title: String,
    val repoName: String,
    val description: String,
    val timeAgo: String,
    val iconType: String // "commit", "star", "repo", "fork", "issue", "generic"
)

enum class RepoSortOption(val displayName: String) {
    RECENTLY_UPDATED("Recently Updated"),
    MOST_STARS("Most Stars"),
    MOST_FORKS("Most Forks"),
    NAME_AZ("Name (A-Z)")
}

enum class GitHubSectionTab(val displayName: String) {
    REPOSITORIES("Repositories"),
    ACTIVITY("Recent Activity"),
    INSIGHTS("Language & Tech")
}
