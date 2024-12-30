package com.example.mobile_programming.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employees")
data class Employee(
    @PrimaryKey val id: Int,
    val name: String,
    val position: String,
    val department: String? // Make position nullable if it can be null
)
