package com.example.bicyclecrud.data

import com.example.bicyclecrud.model.Bicycle
import kotlinx.coroutines.flow.Flow

interface IBicycleRepository {

    fun getAllBicycles(): Flow<List<Bicycle>>

    suspend fun getBicycleById(id: Int): Bicycle?

    suspend fun insertBicycle(bicycle: Bicycle)

    suspend fun updateBicycle(bicycle: Bicycle)

    suspend fun softDeleteBicycle(id: Int)
}
