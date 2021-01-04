package com.example.recognizer

import com.example.recognizer.api.TreflePlantsApi
import org.junit.Test


class TreflePlantsApiTest {


    @Test
    fun test_get_plant_bad_app_key() {
        val api = TreflePlantsApi()
        val call = api.getPlants("Apple", "Bsadsa").execute()

    }

}