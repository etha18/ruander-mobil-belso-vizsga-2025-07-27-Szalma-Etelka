package com.example.bicyclecrud.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bicycles")
data class Bicycle(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val manufacturer: String,
    val model: String,
    val netPrice: Int,
    val isActive: Boolean = true,
    val isDeleted: Boolean = false
)
