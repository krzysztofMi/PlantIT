package com.example.recognizer

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.TextView
import com.example.recognizer.api.WikiApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import org.json.JSONObject

class WikiFragment(private val plantName: String, private val plantSfName: String) : Fragment(R.layout.fragment_wiki) {

    private val request = WikiApi()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getPlantInfo()
    }

    private fun getPlantInfo() {
        val plantSfName = plantSfName.replace("-", " ")
        view?.findViewById<TextView>(R.id.plant_sf_name)?.text = plantSfName
        view?.findViewById<TextView>(R.id.plant_name)?.text = plantName
        view?.findViewById<TextView>(R.id.description)?.movementMethod = ScrollingMovementMethod()
        val call = request.getDescription(plantSfName);
        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        val pages = JSONObject(response.body())
                            .getJSONObject("query")
                            .getJSONObject("pages")
                        pages.keys().forEach {
                            if(it == "-1") {
                                view?.findViewById<TextView>(R.id.description)?.text = getString(R.string.no_data)
                            } else {
                                val desc = pages.getJSONObject(it).getString("extract")
                                view?.findViewById<TextView>(R.id.description)?.text = desc
                            }
                        }

                    }
                }
            }
            override fun onFailure(call: Call<String>, t: Throwable) {
                println(t.message)
                println(t.stackTrace)
            }
        })
    }

}