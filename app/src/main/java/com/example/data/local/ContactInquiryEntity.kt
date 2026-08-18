package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact_inquiries")
data class ContactInquiryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val email: String,
    val subject: String,
    val message: String,
    val projectType: String = "General Inquiry",
    val budgetRange: String = "Discuss Scope",
    val timestamp: Long = System.currentTimeMillis(),
    val status: String = "Received"
)
