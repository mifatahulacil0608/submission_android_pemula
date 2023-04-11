package com.example.mysubmission

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mysubmission.adapter.ListHeroAdapter
import com.example.mysubmission.hero.Hero
import com.example.mysubmission.hero.ProfileData
import com.example.mysubmission.profile.ProfileDetail


class MainActivity : AppCompatActivity() {
    private lateinit var rvHeroes: RecyclerView
    private val list = ArrayList<Hero>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        rvHeroes = findViewById(R.id.rv_heroes)
        //set fixed sizenya
        rvHeroes.setHasFixedSize(true)
        //menambahkan data ke dalam list
        list.addAll(getListHeroes())
        //menampilkan ke layar
        showRecyclerList()
    }

    private fun getListHeroes(): ArrayList<Hero> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataGenre = resources.getStringArray(R.array.genre)
        val dataCast = resources.getStringArray(R.array.cast)

        val listHero = ArrayList<Hero>()
        for (i in dataName.indices) {
            val hero = Hero(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, 1),dataGenre[i],dataCast[i])
            listHero.add(hero)
        }
        return listHero
    }


    //funtion untuk menampilkan ke dalam layar
    private fun showRecyclerList() {
        //jenis layout yang digunakan
        rvHeroes.layoutManager = LinearLayoutManager(this)
        //deklarasi object dari class ListHeroAdapter
        val listHeroAdapter = ListHeroAdapter(list)
        rvHeroes.adapter = listHeroAdapter

        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            rvHeroes.layoutManager = GridLayoutManager(this, 2)
        } else {
            rvHeroes.layoutManager = LinearLayoutManager(this)
        }

        /*listHeroAdapter.setOnItemClickCallback(object : ListHeroAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Hero) {
                showSelectedHero(data)
            }
        })*/
    }

    private fun showSelectedHero(hero: Hero){
        Toast.makeText(this, "kamu memilih "+hero.name, Toast.LENGTH_SHORT).show()}





    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.profile_main,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.profile_menu ->{

                val profile = ProfileData("Ahmad Miftahul Azisz","a251dsx1129@bangkit.academy")


                val intent = Intent(this@MainActivity, ProfileDetail::class.java)
                intent.putExtra(ProfileDetail.KEY,profile)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}