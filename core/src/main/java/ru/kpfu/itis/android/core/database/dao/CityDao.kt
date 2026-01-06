package ru.kpfu.itis.android.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.kpfu.itis.android.core.database.entity.CityEntity

@Dao
interface CityDao {

    @Query("SELECT * from cities")
    fun observeCities(): Flow<List<CityEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(city: CityEntity)

    @Query("DELETE FROM cities WHERE name = :cityName")
    suspend fun deleteCity(cityName: String)

}