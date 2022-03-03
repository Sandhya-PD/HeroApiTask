package com.example.heroapitask.network

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Data::class], version = 1, exportSchema = false)
@TypeConverters(DataClassConverter::class)
abstract class DataRoomDatabase:RoomDatabase() {

    abstract fun dataDao():DataDao

    companion object{

        private var INSTANCE:DataRoomDatabase?=null

        fun getDatabase(context: Context):DataRoomDatabase{
            return INSTANCE ?: synchronized(this){
                val instance= Room.databaseBuilder(
                    context.applicationContext,
                    DataRoomDatabase::class.java,
                    "data_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE=instance
                return instance
            }
        }
    }

}