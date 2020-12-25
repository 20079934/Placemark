package com.w20079934.placemark.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.w20079934.placemark.R
import com.w20079934.placemark.models.PlacemarkModel
import kotlinx.android.synthetic.main.activity_placemark.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast

class PlacemarkActivity : AppCompatActivity(), AnkoLogger {
    val placemarks = ArrayList<PlacemarkModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_placemark)

        btnAdd.setOnClickListener()
        {
            if (placemarkTitle.text.isNotEmpty()) {
                if(placemarkDescription.text.isNotEmpty())
                {
                    placemarks.add(PlacemarkModel(placemarkTitle.text.toString(), placemarkDescription.text.toString()))
                }
                else
                {
                    placemarks.add(PlacemarkModel(placemarkTitle.text.toString()))
                }
                info("current placemarks:")
                for(a in placemarks) {
                    info("title: ${a.title}, description: ${a.description}")
                }
            }
            else {
                toast ("Please Enter a title")
            }
        }
    }
    //test
}