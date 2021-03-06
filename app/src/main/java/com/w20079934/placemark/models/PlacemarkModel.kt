package com.w20079934.placemark.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlacemarkModel(var id: Long = 0, var title: String = "", var description: String = "", var image: String = "") : Parcelable