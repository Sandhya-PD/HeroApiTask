package com.example.heroapitask.network

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


data class Hero(

    val code: Int, // 200
    val `data`: List<Data>
)

@Entity(tableName="hero")
data class Data(
    @PrimaryKey
    @ColumnInfo(name="id")
    val _id: String,
    @ColumnInfo(name="name")
    val name: String,
    @ColumnInfo(name="photo_url")
    val photo: String,
    @ColumnInfo(name="super_name")
    val superhero_name: String
    )






