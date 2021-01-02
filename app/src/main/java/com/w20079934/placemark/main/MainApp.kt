package com.w20079934.placemark.main

import android.app.Application
import com.w20079934.placemark.models.PlacemarkJSONStore
import com.w20079934.placemark.models.PlacemarkStore
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class MainApp : Application(), AnkoLogger {

    lateinit var placemarks : PlacemarkStore

    override fun onCreate() {
        super.onCreate()
        placemarks = PlacemarkJSONStore(applicationContext)
        info("Placemark started")
    }
}