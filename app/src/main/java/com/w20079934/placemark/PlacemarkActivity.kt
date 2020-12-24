package com.w20079934.placemark

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class PlacemarkActivity : AppCompatActivity(), AnkoLogger {
    override fun onCreate(savedInstanceState: Bundle?) {
        info("---------------------------------------------------------------------------------------------Placemark Activity started..")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_placemark)
    }
    //test
}