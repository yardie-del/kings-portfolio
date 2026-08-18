package com.example.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ProjectDao {

    @Query("SELECT * FROM developer_projects ORDER BY is_featured DESC, created_at DESC")
    fun getAllProjects(): Flow<List<ProjectEntity>>

    @Query("SELECT * FROM developer_projects WHERE is_featured = 1 ORDER BY created_at DESC")
    fun getFeaturedProjects(): Flow<List<ProjectEntity>>

    @Query("SELECT * FROM developer_projects WHERE category = :category ORDER BY created_at DESC")
    fun getProjectsByCategory(category: String): Flow<List<ProjectEntity>>

    @Query("SELECT * FROM developer_projects WHERE id = :id LIMIT 1")
    fun getProjectById(id: Long): Flow<ProjectEntity?>

    @Query("SELECT * FROM developer_projects WHERE title LIKE '%' || :query || '%' OR description LIKE '%' || :query || '%' OR technology_tags LIKE '%' || :query || '%'")
    fun searchProjects(query: String): Flow<List<ProjectEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProject(project: ProjectEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProjects(projects: List<ProjectEntity>)

    @Update
    suspend fun updateProject(project: ProjectEntity)

    @Delete
    suspend fun deleteProject(project: ProjectEntity)

    @Query("DELETE FROM developer_projects WHERE id = :id")
    suspend fun deleteProjectById(id: Long)

    @Query("DELETE FROM developer_projects")
    suspend fun clearAllProjects()
}
