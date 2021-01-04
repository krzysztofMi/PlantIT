package com.example.recognizer

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import com.example.recognizer.ml.PlantClassifier

class RecognizeActivity : Activity() {
    private val REQUEST_IMG_CAPTURE = 1
    private val PICK_IMAGE = 100;
    private lateinit var pC : PlantClassifier

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recognize)
        pC = PlantClassifier(this, "label.txt", "model.tflite")
        prepareButton()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQUEST_IMG_CAPTURE && resultCode == RESULT_OK) {
            val bitmap = data?.extras?.get("data") as Bitmap
            val imgPath = MediaStore.Images.Media.insertImage(contentResolver, bitmap,
                pC.recognizedLabel, "Image of ${pC.recognizedLabel}")
            recognizeImg(imgPath, bitmap, true)
        }else if(requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            val imgPath = data?.data
            val bitmap = MediaStore.Images.Media.getBitmap(
                this.contentResolver,
                imgPath
            )
            recognizeImg(imgPath.toString(), bitmap, false)
        }else{
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun prepareButton() {
        findViewById<Button>(R.id.photo_bttn).setOnClickListener {
            try {
                startActivityForResult(Intent(MediaStore.ACTION_IMAGE_CAPTURE), REQUEST_IMG_CAPTURE)
            } catch (e: ActivityNotFoundException) {

            }
        }
        findViewById<Button>(R.id.storage_bttn).setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, PICK_IMAGE)
        }
        findViewById<Button>(R.id.cancel_bttn).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun recognizeImg(imgPath: String, bitmap: Bitmap, store: Boolean) {
        pC.classifyImage(bitmap)
        startActivity(
            Intent(this, PlantActivity::class.java)
                .apply {
                    putExtra("IMG_PATH", imgPath)
                    putExtra("LABEL", pC.recognizedLabel)
                    putExtra("NAME", pC.recognizedLabel)}
                    )
    }

}

