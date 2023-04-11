package com.example.mysubmission.detailactivity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mysubmission.R
import com.example.mysubmission.hero.Hero

class DetailActivity : AppCompatActivity(){
    private lateinit var tvDetailActivityImage : ImageView
    private lateinit var tvDescription : TextView
    private lateinit var tvDetailName : TextView
    private lateinit var tvShare : Button
    private lateinit var send : String
    private lateinit var tvGenre : TextView
    private lateinit var tvCast : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        tvDetailActivityImage = findViewById(R.id.detail_activity_image)
        tvDetailName = findViewById(R.id.detail_activity_name)
        tvDescription = findViewById(R.id.detail_activity_description)
        tvGenre = findViewById(R.id.detail_activity_genre)
        tvCast = findViewById(R.id.detail_activity_cast)




        val heroDetail = if(Build.VERSION.SDK_INT >= 33){
            intent.getParcelableExtra("key_hero", Hero::class.java)
        }else{
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("key_hero")
        }
        if (heroDetail != null){
            tvDetailActivityImage.setImageResource(heroDetail.photo)
            //Glide.with(this).load(heroDetail.photo).into(tvDetailActivityImage)
            tvDetailName.text = heroDetail.name
            tvDescription.text = heroDetail.description
            tvGenre.text = heroDetail.genre
            tvCast.text = heroDetail.cast

        }

        send = """
            Nama Film : ${heroDetail?.name}
            Deskripsi : ${heroDetail?.description}
        """.trimIndent()



    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.share_button,menu)
        return super.onCreateOptionsMenu(menu)
    }override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.share ->{

                val share = Intent(Intent.ACTION_SEND)
                share.putExtra(Intent.EXTRA_TEXT, send)
                share.setType("text/plain")
                startActivity(share)
            }
        }
        return super.onOptionsItemSelected(item)
    }


}