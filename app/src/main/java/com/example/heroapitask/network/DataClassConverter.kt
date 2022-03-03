package com.example.heroapitask.network

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DataClassConverter {
    @TypeConverter
    fun toGenre(json: String): List<Data> {
        val type = object : TypeToken<List<Data>>() {}.type
        return Gson().fromJson(json, type)
    }

    @TypeConverter
    fun toJson(genres: List<Data>):String {

            val type = object: TypeToken<List<Data>>() {}.type
            return Gson().toJson(genres, type)

    }
}