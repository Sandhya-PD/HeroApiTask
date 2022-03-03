package com.example.heroapitask.ui

import android.app.Application
import com.example.heroapitask.network.DataRoomDatabase

class HeroApplication:Application() {
    val database:DataRoomDatabase by lazy { DataRoomDatabase.getDatabase(this) }
}