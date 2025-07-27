package com.example.bicyclecrud.data

import androidx.room.*
import com.example.bicyclecrud.model.Bicycle
import kotlinx.coroutines.flow.Flow

@Dao
interface BicycleDao {

    @Query("SELECT * FROM bicycles WHERE id = :id")
    suspend fun getById(id: Int): Bicycle?

    @Query("SELECT * FROM bicycles WHERE isDeleted = 0 ORDER BY id")
    fun getAll(): Flow<List<Bicycle>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bicycle: Bicycle)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg bicycles: Bicycle)

    @Update
    suspend fun update(bicycle: Bicycle)

    @Query("UPDATE bicycles SET isDeleted = 1 WHERE id = :id")
    suspend fun softDelete(id: Int)
}
