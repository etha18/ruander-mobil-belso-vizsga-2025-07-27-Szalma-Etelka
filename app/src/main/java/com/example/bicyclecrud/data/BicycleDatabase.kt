package com.example.bicyclecrud.data

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.bicyclecrud.model.Bicycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [Bicycle::class],
    version = 2,
    exportSchema = false
)
abstract class BicycleDatabase : RoomDatabase() {
    abstract fun bicycleDao(): BicycleDao

    companion object {
        @Volatile
        var INSTANCE: BicycleDatabase? = null

        fun createCallback(): Callback {
            return object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    CoroutineScope(Dispatchers.IO).launch {
                        INSTANCE?.bicycleDao()?.insertAll(
                            Bicycle(1, "Specialized", "Roubaix SL8 Sport", 1200000, true, false),
                            Bicycle(2, "Giant", "TCR Advanced 1 Disc", 1150000, false, false),
                            Bicycle(3, "Merida", "Scultura 6000", 1100000, true, true)
                        )
                    }
                }
            }
        }
    }
}
