package com.example.recognizer

import com.example.recognizer.api.Plant
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.recognizer.api.Data
import com.example.recognizer.api.TreflePlantsApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlantActivity : AppCompatActivity() {
    var plant: Data? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant)
        setFragments()
    }

    private fun setFragments() {
        val imgPath = intent.getStringExtra("IMG_PATH")
        val sfName = intent.getStringExtra("LABEL")
        val name = intent.getStringExtra("NAME")
        val infoFragment = PlantInfoFragment(sfName, imgPath)
        val imagesFragment = PlantImagesFragment(sfName)
        val wikiFragment = WikiFragment(name, sfName)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.plantFragment, infoFragment)
            commit()
        }
        findViewById<ImageButton>(R.id.plant_info_bttn).setOnClickListener{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.plantFragment, infoFragment)
                commit()
            }
        }
        findViewById<ImageButton>(R.id.plant_pic_bttn).setOnClickListener{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.plantFragment, imagesFragment)
                commit()
            }
        }
        findViewById<ImageButton>(R.id.wiki_bttn).setOnClickListener{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.plantFragment, wikiFragment)
                commit()
            }
        }
    }



}

