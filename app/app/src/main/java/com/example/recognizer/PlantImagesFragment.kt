package com.example.recognizer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recognizer.adapter.PlantGalleryAdapter
import com.example.recognizer.adapter.PlantRecyclerAdapter
import com.example.recognizer.api.Data
import com.example.recognizer.api.Plant
import com.example.recognizer.api.TreflePlantsApi
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlantImagesFragment(private val plantName: String) : Fragment(R.layout.fragment_plant_images) {

    private val request = TreflePlantsApi()
    private var recycler: RecyclerView? = null
    private var adapter = PlantGalleryAdapter(listOf())
    private var plant: Data? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = view.findViewById(R.id.gallery_recycler)
        val layoutManager = FlexboxLayoutManager(view.context)
        layoutManager.flexDirection = FlexDirection.COLUMN;
        layoutManager.justifyContent = JustifyContent.FLEX_END;
        recycler?.layoutManager = layoutManager
        recycler?.adapter = adapter
        getPlant()
        initButton()
    }

    private fun getPlant() {
        val call = request.getPlant(plantName, getString(R.string.app_key))
        call.enqueue(object : Callback<Plant> {
            override fun onResponse(call: Call<Plant>, response: Response<Plant>) {
                if (response.isSuccessful) {
                    plant = response.body()!!.data
                    initView()
                }
            }

            override fun onFailure(call: Call<Plant>, t: Throwable) {
                println(t.message)
                println(t.stackTrace)
            }
        })
    }

    private fun initButton() {
        view?.findViewById<ImageButton>(R.id.flower_bttn)?.setOnClickListener{
            val imgList = plant?.images?.flower?.map { it.image_url }
            changeAdapter(imgList)
        }
        view?.findViewById<ImageButton>(R.id.fruit_bttn)?.setOnClickListener{
            val imgList = plant?.images?.fruit?.map { it.image_url }
            changeAdapter(imgList)
        }
        view?.findViewById<ImageButton>(R.id.leaf_bttn)?.setOnClickListener{
            val imgList = plant?.images?.leaf?.map { it.image_url }
            changeAdapter(imgList)
        }
        view?.findViewById<ImageButton>(R.id.bark_bttn)?.setOnClickListener{
            val imgList = plant?.images?.bark?.map { it.image_url }
            changeAdapter(imgList)
        }
        view?.findViewById<ImageButton>(R.id.habit_bttn)?.setOnClickListener{
            val imgList = plant?.images?.habit?.map { it.image_url }
            changeAdapter(imgList)
        }

    }

    private fun changeAdapter(imgList: List<String>?) {
        adapter.setImages(imgList)
        adapter.notifyDataSetChanged()
    }

    private fun initView() {
        val imgList = plant?.images?.flower?.map { it.image_url }
        changeAdapter(imgList)
    }
}

