package com.example.heroapitask.network


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DataDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(data:List<Data>)

    @Query("SELECT * FROM hero")
    suspend fun getAllData(): List<Data>

}