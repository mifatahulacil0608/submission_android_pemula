package com.example.mysubmission.hero

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
// data class

@Parcelize
data class Hero(
    val name: String,
    val description: String,
    val photo: Int,
    val genre : String,
    val cast : String
) : Parcelable