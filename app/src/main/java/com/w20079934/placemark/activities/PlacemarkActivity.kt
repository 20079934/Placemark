package com.w20079934.placemark.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.w20079934.placemark.R
import com.w20079934.placemark.helpers.readImage
import com.w20079934.placemark.helpers.readImageFromPath
import com.w20079934.placemark.helpers.showImagePicker
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
    val IMAGE_REQUEST=1

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
            if(placemark.image != "") {
                placemarkImage.setImageBitmap(readImageFromPath(this, placemark.image))
                chooseImage.text = getString(R.string.button_changeImage)
            }
        }

        chooseImage.setOnClickListener {
            showImagePicker(this, IMAGE_REQUEST)
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
                    placemark.title = placemarkTitle.text.toString()
                    placemark.description = placemarkDescription.text.toString()
                    app.placemarks.create(placemark.copy())
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            IMAGE_REQUEST -> {
                if (data != null) {
                    placemark.image = data.data.toString()
                    placemarkImage.setImageBitmap(readImage(this,resultCode,data))
                    chooseImage.text = getString(R.string.button_changeImage)
                }
            }
        }
    }

}