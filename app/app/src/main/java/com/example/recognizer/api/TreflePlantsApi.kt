package com.example.recognizer.api

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.lang.reflect.GenericSignatureFormatError

interface TreflePlantsApi {
    @GET("species/{name}")
    fun getPlant(
        @Path("name") name: String,
        @Query("token") app_key: String): Call<Plant>

    @GET("plants/search")
    fun getPlants(
        @Query("q") query: String,
        @Query("token") app_key: String): Call<String>

    companion object {
        operator fun invoke(): TreflePlantsApi {

            val okHttpClient = OkHttpClient.Builder()
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://trefle.io/api/v1/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TreflePlantsApi::class.java)
        }


    }
}

