package com.shalatan.doggo.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDogImage(dogEntity: DogEntity)

    @Query("SELECT * FROM dog_db")
    suspend fun getAllDogImages(): List<DogEntity>

    @Query("DELETE FROM dog_db")
    suspend fun clearData()
}