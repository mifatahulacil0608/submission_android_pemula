package com.example.mysubmission.hero
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProfileData( val name : String, val email : String ):Parcelable
