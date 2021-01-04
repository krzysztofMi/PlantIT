package com.example.recognizer.api

import android.content.res.Resources
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WikiApi {

    @GET("?format=json&action=query&prop=extracts&exsentences=10&redirects&exlimit=2&explaintext=1&formatversion=1")
    fun getDescription(
        @Query("titles") title: String) : Call<String>

    companion object {
        operator fun invoke(): WikiApi {
            val okHttpClient = OkHttpClient.Builder()
                .build()
            //val locale = Resources.getSystem().configuration.locale.language
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://pl.wikipedia.org/w/api.php/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
                .create(WikiApi::class.java)
        }
    }
}