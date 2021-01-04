package com.example.recognizer

import com.example.recognizer.api.WikiApi
import org.json.JSONObject
import org.junit.Test

class WikiApiTest {
    private var wikiApi = WikiApi()

    @Test
    fun test_get_plant_description_status_code_ok() {
        val request = wikiApi.getDescription("Malus pumila").execute()
        assert(request.isSuccessful)
    }


    @Test
    fun test_get_plant_description() {
        val request = wikiApi.getDescription("Malus pumila").execute()
        val json = JSONObject(request.body())
            //        .getJSONObject("query")
        println(json)
//            .getJSONObject("pages")
//        json.keys().forEach {
//            if(it == "-1") {
//                assert(false)
//            }
//        }
//        assert(true)
    }

}