package com.shalatan.doggo.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dog_db")
data class DogEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0, val url: String
)
