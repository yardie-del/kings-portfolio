package com.example.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface InquiryDao {
    @Query("SELECT * FROM contact_inquiries ORDER BY timestamp DESC")
    fun getAllInquiries(): Flow<List<ContactInquiryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInquiry(inquiry: ContactInquiryEntity): Long

    @Delete
    suspend fun deleteInquiry(inquiry: ContactInquiryEntity)

    @Query("DELETE FROM contact_inquiries")
    suspend fun clearAllInquiries()
}
