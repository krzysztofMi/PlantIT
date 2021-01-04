package com.example.recognizer.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recognizer.PlantActivity
import com.example.recognizer.R
import com.example.recognizer.api.PlantDTO
import com.squareup.picasso.Picasso

class PlantRecyclerAdapter(private var ctx: Context, private var plants: List<PlantDTO>) :
    RecyclerView.Adapter<PlantRecyclerAdapter.PlantHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            PlantHolder =
            PlantHolder(LayoutInflater.from(parent.context).inflate(R.layout.plant_small, parent, false))

    override fun onBindViewHolder(holder: PlantHolder, position: Int) {
        holder.name.text = plants[position].common_name
        holder.scientificName.text = plants[position].scientific_name
        Picasso.get().load(plants[position].image_url).into(holder.photo)
        holder.photoPath = plants[position].image_url
        holder.searchName = plants[position].search_name
    }

    override fun getItemCount() = plants.size

    fun setPlants(plants: List<PlantDTO>) {this.plants = plants}

    class PlantHolder(v: View) : RecyclerView.ViewHolder(v),
                    View.OnClickListener{
        val name: TextView = v.findViewById(R.id.normal_name)
        val scientificName: TextView = v.findViewById(R.id.scientific_name)
        val photo: ImageView = v.findViewById(R.id.small_plant)
        var photoPath = ""
        var searchName = ""

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
             v?.context?.startActivity(Intent(v?.context, PlantActivity::class.java).apply {
                 putExtra("IMG_PATH", photoPath)
                 putExtra("LABEL", searchName)
                 putExtra("NAME", name.text.toString())
             })
        }

    }
}

