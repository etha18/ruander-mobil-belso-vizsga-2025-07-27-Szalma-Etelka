package com.example.bicyclecrud.di

import android.app.Application
import androidx.room.Room
import com.example.bicyclecrud.data.BicycleDao
import com.example.bicyclecrud.data.BicycleDatabase
import com.example.bicyclecrud.data.BicycleRepository
import com.example.bicyclecrud.data.IBicycleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBicycleDatabase(app: Application): BicycleDatabase {
        val db = Room.databaseBuilder(
            app,
            BicycleDatabase::class.java,
            "bicycle_db"
        )
            .fallbackToDestructiveMigration()
            .addCallback(BicycleDatabase.createCallback())
            .build()

        // Fontos! Állítsuk be az INSTANCE-t is, hogy callback-ből elérhető legyen
        BicycleDatabase.INSTANCE = db

        return db
    }

    @Provides
    @Singleton
    fun provideBicycleDao(db: BicycleDatabase): BicycleDao {
        return db.bicycleDao()
    }

    @Provides
    @Singleton
    fun provideBicycleRepository(
        bicycleDao: BicycleDao
    ): IBicycleRepository {
        return BicycleRepository(bicycleDao)
    }
}
