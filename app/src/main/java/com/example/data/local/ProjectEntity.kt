package com.example.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room Database Entity representing a developer project.
 */
@Entity(tableName = "developer_projects")
data class ProjectEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "technology_tags")
    val technologyTags: List<String> = emptyList(),

    @ColumnInfo(name = "slug")
    val slug: String = "",

    @ColumnInfo(name = "category")
    val category: String = "Startup Projects",

    @ColumnInfo(name = "tagline")
    val tagline: String = "",

    @ColumnInfo(name = "problem_solved")
    val problemSolved: String = "",

    @ColumnInfo(name = "solution")
    val solution: String = "",

    @ColumnInfo(name = "github_url")
    val githubUrl: String = "",

    @ColumnInfo(name = "live_demo_url")
    val liveDemoUrl: String = "",

    @ColumnInfo(name = "status")
    val status: String = "Production MVP",

    @ColumnInfo(name = "is_featured")
    val isFeatured: Boolean = false,

    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis()
)
