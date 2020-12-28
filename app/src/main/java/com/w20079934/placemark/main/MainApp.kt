package com.w20079934.placemark.main

import android.app.Application
import com.w20079934.placemark.models.PlacemarkMemStore
import com.w20079934.placemark.models.PlacemarkModel
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class MainApp : Application(), AnkoLogger {

    val placemarks = PlacemarkMemStore()

    override fun onCreate() {
        super.onCreate()
        info("Placemark started")
    }
}