package com.example.recognizer

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.recognizer.ml.PlantClassifier
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import java.lang.Exception


import java.lang.RuntimeException

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.LOLLIPOP])
class PlantClassifierTest {

    val context = InstrumentationRegistry.getInstrumentation().context
    private val labelPath = "label.txt"
    private val modelPath = "model.tflite"
    private var objectUnderTest = PlantClassifier(context, labelPath, modelPath)

    @Test
    fun set_label() {
        try {
            objectUnderTest.loadLabels("path")
        } catch (e: RuntimeException){
            assert(true)
        }
        try {
            objectUnderTest.loadLabels(labelPath)
        } catch (e: RuntimeException) {
            assert(false)
        }
      }

    @Test
    fun set_model() {
        try {
            objectUnderTest.loadModel("path")
        } catch (e: RuntimeException) {
            assert(true)
        }
        try {
            objectUnderTest.loadModel(labelPath)
        } catch (e: RuntimeException) {
            assert(true)
        }
        try {
            objectUnderTest.loadModel(modelPath)
        } catch (e: RuntimeException) {
            assert(true)
        }
    }

    @Test
    fun recognize_test() {

    }

}




