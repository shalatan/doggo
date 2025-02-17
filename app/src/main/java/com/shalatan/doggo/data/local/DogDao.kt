package com.shalatan.doggo.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DogDao {

    @Insert
    suspend fun insertDog(dogEntity: DogEntity)

    @Query("SELECT * FROM dog_db ORDER BY id DESC")
    suspend fun getAllDogImages(): List<DogEntity>

    @Query("DELETE FROM dog_db")
    suspend fun clearData()

    @Query("SELECT COUNT(id) FROM dog_db")
    suspend fun getDogCount(): Int

    @Query("DELETE FROM dog_db WHERE id IN (SELECT id FROM dog_db ORDER BY id ASC LIMIT 1)")
    suspend fun deleteOldestDog()
}