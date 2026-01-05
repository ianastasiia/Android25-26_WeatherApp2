package ru.kpfu.itis.android.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.kpfu.itis.android.core.database.dao.CityDao
import ru.kpfu.itis.android.core.database.entity.CityEntity

@Database(
    entities = [CityEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cityDao(): CityDao
}