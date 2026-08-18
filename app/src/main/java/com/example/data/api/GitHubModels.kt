package com.example.data.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GitHubUserResponse(
    @Json(name = "login") val login: String,
    @Json(name = "id") val id: Long = 0L,
    @Json(name = "avatar_url") val avatarUrl: String? = null,
    @Json(name = "name") val name: String? = null,
    @Json(name = "company") val company: String? = null,
    @Json(name = "blog") val blog: String? = null,
    @Json(name = "location") val location: String? = null,
    @Json(name = "bio") val bio: String? = null,
    @Json(name = "twitter_username") val twitterUsername: String? = null,
    @Json(name = "public_repos") val publicRepos: Int = 0,
    @Json(name = "public_gists") val publicGists: Int = 0,
    @Json(name = "followers") val followers: Int = 0,
    @Json(name = "following") val following: Int = 0,
    @Json(name = "created_at") val createdAt: String? = null,
    @Json(name = "html_url") val htmlUrl: String? = null
)

@JsonClass(generateAdapter = true)
data class GitHubRepoResponse(
    @Json(name = "id") val id: Long = 0L,
    @Json(name = "name") val name: String,
    @Json(name = "full_name") val fullName: String? = null,
    @Json(name = "private") val isPrivate: Boolean = false,
    @Json(name = "html_url") val htmlUrl: String? = null,
    @Json(name = "description") val description: String? = null,
    @Json(name = "fork") val isFork: Boolean = false,
    @Json(name = "stargazers_count") val stargazersCount: Int = 0,
    @Json(name = "watchers_count") val watchersCount: Int = 0,
    @Json(name = "language") val language: String? = null,
    @Json(name = "forks_count") val forksCount: Int = 0,
    @Json(name = "open_issues_count") val openIssuesCount: Int = 0,
    @Json(name = "topics") val topics: List<String>? = null,
    @Json(name = "updated_at") val updatedAt: String? = null,
    @Json(name = "created_at") val createdAt: String? = null
)

@JsonClass(generateAdapter = true)
data class GitHubEventActor(
    @Json(name = "login") val login: String? = null,
    @Json(name = "avatar_url") val avatarUrl: String? = null
)

@JsonClass(generateAdapter = true)
data class GitHubEventRepo(
    @Json(name = "id") val id: Long = 0L,
    @Json(name = "name") val name: String? = null,
    @Json(name = "url") val url: String? = null
)

@JsonClass(generateAdapter = true)
data class GitHubEventCommit(
    @Json(name = "sha") val sha: String? = null,
    @Json(name = "message") val message: String? = null
)

@JsonClass(generateAdapter = true)
data class GitHubEventPayload(
    @Json(name = "action") val action: String? = null,
    @Json(name = "ref") val ref: String? = null,
    @Json(name = "ref_type") val refType: String? = null,
    @Json(name = "commits") val commits: List<GitHubEventCommit>? = null,
    @Json(name = "description") val description: String? = null
)

@JsonClass(generateAdapter = true)
data class GitHubEventResponse(
    @Json(name = "id") val id: String,
    @Json(name = "type") val type: String,
    @Json(name = "actor") val actor: GitHubEventActor? = null,
    @Json(name = "repo") val repo: GitHubEventRepo? = null,
    @Json(name = "payload") val payload: GitHubEventPayload? = null,
    @Json(name = "created_at") val createdAt: String? = null
)
