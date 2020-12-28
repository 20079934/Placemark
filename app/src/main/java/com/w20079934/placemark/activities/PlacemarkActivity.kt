package com.w20079934.placemark.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.w20079934.placemark.R
import com.w20079934.placemark.main.MainApp
import com.w20079934.placemark.models.PlacemarkModel
import kotlinx.android.synthetic.main.activity_placemark.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast

class PlacemarkActivity : AppCompatActivity(), AnkoLogger {

    lateinit var app : MainApp;
    var placemark = PlacemarkModel();
    var edit = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_placemark)
        app = application as MainApp;

        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)

        if(intent.hasExtra("placemark_edit")) {
            edit = true
            placemark = intent.extras?.getParcelable<PlacemarkModel>("placemark_edit")!!
            placemarkTitle.setText(placemark.title)
            placemarkDescription.setText(placemark.description)
            btnAdd.text = getString(R.string.button_updatePlacemark)
        }


        btnAdd.setOnClickListener()
        {
            if (placemarkTitle.text.isNotEmpty()) {
                if(edit) {
                    placemark.title = placemarkTitle.text.toString()
                    placemark.description = placemarkDescription.text.toString()
                    app.placemarks.update(placemark)
                    info("------------updated placemark!")

                } else {
                    app.placemarks.create(
                        PlacemarkModel(
                            0,
                            placemarkTitle.text.toString(),
                            placemarkDescription.text.toString()
                        )
                    )
                    info("------------inserted placemark!")
                }

                setResult(AppCompatActivity.RESULT_OK)
                finish()
            } else {
                toast(getString(R.string.menu_invalidName))
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_cancel -> {
                setResult(AppCompatActivity.RESULT_CANCELED)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
    //test
}