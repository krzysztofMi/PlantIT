package com.example.recognizer

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.recognizer.api.Data
import com.example.recognizer.api.Plant
import com.example.recognizer.api.TreflePlantsApi
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_plant_info.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlantInfoFragment(private val plantName: String, private var imgPath: String)
        : Fragment(R.layout.fragment_plant_info) {

    private val request = TreflePlantsApi()
    private var plant: Data? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getPlant()
    }

    private fun getPlant() {
        val call = request.getPlant(plantName, getString(R.string.app_key))
        call.enqueue(object : Callback<Plant> {
            override fun onResponse(call: Call<Plant>, response: Response<Plant>) {
                if (response.isSuccessful) {
                    plant = response.body()!!.data
                    println(plant)
                    setFields()
                }
            }

            override fun onFailure(call: Call<Plant>, t: Throwable) {
                println(t.message)
                println(t.stackTrace)
            }

        })
    }

    private fun setFields() {
        val button = view?.findViewById<Button>(R.id.edible)
        if(plant?.edible != null) {
            val color = if(plant?.edible == true) Color.GREEN else Color.RED
            button?.setBackgroundColor(color)
        }else {
            button?.setBackgroundColor(Color.GRAY)
        }
        if(plant?.edible_part != null) {
            view?.findViewById<TextView>(R.id.edible_part)?.text = plant!!.edible_part[0]
        }else {
            view?.findViewById<TextView>(R.id.edible_part)?.text = "no data"
        }
        val plantSpec = plant?.specifications
        if(plantSpec == null) {
            view?.findViewById<TextView>(R.id.growth_form)?.text = "no data"
            view?.findViewById<TextView>(R.id.ligneous_type)?.text = "no data"
            view?.findViewById<TextView>(R.id.growth_habit)?.text = "no data"
            view?.findViewById<TextView>(R.id.growth_rate)?.text = "no data"
            view?.findViewById<TextView>(R.id.average_height)?.text = "no data"
            view?.findViewById<TextView>(R.id.max_height)?.text = "no data"
        } else {
            view?.findViewById<TextView>(R.id.growth_form)?.text = plantSpec?.growth_form ?: "no data"
            view?.findViewById<TextView>(R.id.ligneous_type)?.text = plantSpec?.ligneous_type ?: "no data"
            view?.findViewById<TextView>(R.id.growth_habit)?.text = plantSpec?.growth_habit ?: "no data"
            view?.findViewById<TextView>(R.id.growth_rate)?.text = plantSpec?.growth_rate ?: "no data"
            view?.findViewById<TextView>(R.id.average_height)?.text = plantSpec?.average_height.cm.toString() ?: "no data"
            view?.findViewById<TextView>(R.id.max_height)?.text = plantSpec?.maximum_height.cm.toString() ?: "no data"
        }
        Picasso.get().load(imgPath).into(view?.findViewById<ImageView>(R.id.plant_img))
        view?.findViewById<TextView>(R.id.plant_name)?.text = plant?.common_name
        view?.findViewById<TextView>(R.id.plant_sf_name)?.text = plant?.scientific_name
    }
}