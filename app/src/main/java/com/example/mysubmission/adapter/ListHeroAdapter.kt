package com.example.mysubmission.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mysubmission.R
import com.example.mysubmission.detailactivity.DetailActivity
import com.example.mysubmission.hero.Hero

class ListHeroAdapter (private val listHero: ArrayList<Hero>) : RecyclerView.Adapter<ListHeroAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    // menginisialisasi setiap komponen pada layout item dengan menggunakan itemView.findViewById

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName : TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDescription : TextView = itemView.findViewById(R.id.tv_item_description)

    }



    // digunakan untuk membuat ViewHolder baru yang berisi layout item yang digunakan,
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        //item wajib yang harus dimiliki
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_hero,parent,false)
        return ListViewHolder(view)
    }


    //menetapkan data yang ada ke dalam ViewHolder sesuai dengan posisinya dengan menggunakan listHero[position]
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        //inisialiasi atau deklarasi dari data class ke dalam val list hero menggunakan destructing
        val (name,description,photo,genre,cast) = listHero[position]
        //inisialisasi object holder.imgphoto-tvdescription dengan data yg didapat dari data class
        holder.imgPhoto.setImageResource(photo)

        holder.tvName.text = name
        holder.tvDescription.text = description


        /*//membuat setonclicklistener
        holder.itemView.setOnClickListener{
            Toast.makeText(holder.itemView.context,"kamu memilih "+ listHero[holder.adapterPosition].name, Toast.LENGTH_SHORT).show()
        }*/
        //holder.itemView.setOnClickListener{onItemClickCallback.onItemClicked(listHero[holder.adapterPosition]) }
        holder.itemView.setOnClickListener{
            val intentDetail = Intent(holder.itemView.context, DetailActivity::class.java)

            intentDetail.putExtra("key_hero", listHero[holder.adapterPosition])
            holder.itemView.context.startActivity(intentDetail)
        }
    }
    // function untuk menghjitung banyaknya array list
    override fun getItemCount() = listHero.size

    interface OnItemClickCallback{
        fun onItemClicked(data : Hero)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }



}