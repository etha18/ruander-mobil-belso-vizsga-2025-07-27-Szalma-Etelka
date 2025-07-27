package com.example.bicyclecrud.data

import android.util.Log
import com.example.bicyclecrud.model.Bicycle
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BicycleRepository @Inject constructor(
    private val bicycleDao: BicycleDao
) : IBicycleRepository {

    override fun getAllBicycles(): Flow<List<Bicycle>> {
        return bicycleDao.getAll()
    }

    override suspend fun getBicycleById(id: Int): Bicycle? {
        return bicycleDao.getById(id)
    }

    override suspend fun insertBicycle(bicycle: Bicycle) {
        Log.d("INSERT", "Insert bicycle: $bicycle")
        bicycleDao.insert(bicycle)
    }

    override suspend fun updateBicycle(bicycle: Bicycle) {
        Log.d("UPDATE", "Update bicycle: $bicycle")
        bicycleDao.update(bicycle)
    }

    override suspend fun softDeleteBicycle(id: Int) {
        Log.d("DELETE", "Soft delete bicycle ID: $id")
        bicycleDao.softDelete(id)
    }
}
