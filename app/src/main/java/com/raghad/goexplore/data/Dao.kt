package com.raghad.goexplore.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface Dao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTrip(trip: Trip)

    @Query("SELECT * FROM trips_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Trip>>


}