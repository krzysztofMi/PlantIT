package com.example.recognizer.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.recognizer.R
import com.squareup.picasso.Picasso


class PlantGalleryAdapter(private var images: List<String>?) :
    RecyclerView.Adapter<PlantGalleryAdapter.ImageHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ImageHolder =
        ImageHolder(LayoutInflater.from(parent.context).inflate(R.layout.plant_gallery, parent, false))

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        Picasso.get().load(images?.get(position)).into(holder.img)
    }

    override fun getItemCount() = images?.size ?: 0

    fun setImages(images: List<String>?) {this.images = images ?: listOf()}

    class ImageHolder(v: View) : RecyclerView.ViewHolder(v) {
        var img: ImageView = v.findViewById(R.id.gallery_image)
    }
}

