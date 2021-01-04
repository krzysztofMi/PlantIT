package com.example.recognizer

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recognizer.adapter.PlantRecyclerAdapter
import com.example.recognizer.api.PlantDTO
import com.example.recognizer.api.TreflePlantsApi
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : Activity() {

    private val request = TreflePlantsApi()
    private var recycler: RecyclerView? = null
    private var adapter = PlantRecyclerAdapter(this, listOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        recycler = findViewById(R.id.plantRecycler)
        recycler?.layoutManager = LinearLayoutManager(this)
        recycler?.adapter = adapter
        search()
    }

    private fun search() {
        val editText = findViewById<EditText>(R.id.search)
        editText.setOnFocusChangeListener { _, _ ->
            if(editText.text.toString() == getString(R.string.search_field)) {
                editText.setText("")
            }
        }
        val maxTextLength = 30
        editText.filters = Array(1) { InputFilter.LengthFilter(maxTextLength)}

        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                request.getPlants(s.toString(), getString(R.string.app_key))
                    .enqueue(object : Callback<String> {
                        override fun onResponse(
                            call: Call<String>,
                            response: Response<String>
                        ) {
                            if (response.body() != null) {
                                val arr = JSONObject(response.body())
                                    .getJSONArray("data")
                                val plants = arrayListOf<PlantDTO>()
                                for (i in 0 until arr.length()) {
                                    val jsonObject = arr.getJSONObject(i)
                                    val url = jsonObject.getString("image_url")
                                    val name = jsonObject.getString("common_name")
                                    val scientName = jsonObject.getString("scientific_name")
                                    val searchName = jsonObject.getString("slug")
                                    plants.add(PlantDTO(url, name, scientName, searchName))
                                }
                                adapter.setPlants(plants)
                                adapter.notifyDataSetChanged()
                            }
                        }

                        override fun onFailure(call: Call<String>, t: Throwable) {
                            println(t.toString())
                        }
                    })
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
    }
}