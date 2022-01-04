package com.raghad.goexplore.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Trip::class], version = 1, exportSchema = false)
abstract class TripDatabase: RoomDatabase() {

//    abstract fun dao(): Dao
//
//    companion object{
//
//        @Volatile
//        private var INSTANCE: TripDatabase? = null
//
//        fun getDatabase(context: Context): TripDatabase{
//            val tempInstance = INSTANCE
//            if(tempInstance != null){
//                return tempInstance
//            }
//
//            synchronized(this){
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    TripDatabase::class.java,
//                    "trip_datbace"
//                ).build()
//                INSTANCE = instance
//                return instance
//            }
//        }
//
//    }
}