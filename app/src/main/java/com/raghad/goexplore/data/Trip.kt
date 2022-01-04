package com.raghad.goexplore.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trips_table")
data class Trip (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
            val title: String,


)