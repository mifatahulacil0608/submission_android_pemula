package com.example.mysubmission.profile

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mysubmission.R
import com.example.mysubmission.hero.ProfileData


class ProfileDetail : AppCompatActivity() {
    companion object {
        const val KEY = "PROFILE"
    }
    private lateinit var profilePicture : ImageView
    private lateinit var profileName : TextView
    private lateinit var profileEmail : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_detail)


        profileName = findViewById(R.id.profile_name)
        profileEmail = findViewById(R.id.profile_email)

        val person = if(Build.VERSION.SDK_INT >= 33){
            intent.getParcelableExtra(KEY, ProfileData::class.java)
        }else{
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(KEY)
        }

        if (person !=null){


            profileName.text = person.name
            profileEmail.text = person.email
        }

        val buttonBack = supportActionBar
        buttonBack?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}