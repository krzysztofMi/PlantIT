package com.example.recognizer.ml

import android.content.Context
import android.graphics.Bitmap
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.support.common.FileUtil
import org.tensorflow.lite.support.common.TensorProcessor
import org.tensorflow.lite.support.common.ops.NormalizeOp
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ops.ResizeOp
import org.tensorflow.lite.support.label.TensorLabel
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.io.IOException

class PlantClassifier(
    private val context: Context,
    private val labelPath: String,
    private val modelPath: String) {
    private lateinit var labels: List<String>
    private lateinit var interpreter: Interpreter
    private lateinit var imgProcessor: ImageProcessor
    private lateinit var tensorImage: TensorImage
    public var recognizedLabel: String? = null
    public var probability: Float? = null
    private val imgWidht = 180
    private val imgHeight = 180

    fun loadLabels(labelPath: String) {
        try {
            labels = FileUtil.loadLabels(context, labelPath)
        }catch (e: IOException) {
            throw RuntimeException("Load label error!", e)
        }
    }

    fun loadModel(modelPath: String) {
        try{
            interpreter = Interpreter(FileUtil.loadMappedFile(context, modelPath))
            tensorImage = TensorImage(interpreter.getInputTensor(0).dataType())
        } catch (e: IOException){
            throw RuntimeException(e.message)
        }
    }

    private fun prepareImageProcessor() {
        imgProcessor = ImageProcessor.Builder()
            .add(ResizeOp(imgHeight, imgWidht, ResizeOp.ResizeMethod.BILINEAR))
            .build()
    }

    fun classifyImage(bitmap: Bitmap){
        loadLabels(labelPath)
        loadModel(modelPath)
        prepareImageProcessor()
        tensorImage.load(bitmap)
        tensorImage = imgProcessor.process(tensorImage)
        val shape = interpreter.getOutputTensor(0).shape()
        val dType = interpreter.getOutputTensor(0).dataType()
        val probabilityBuffer =
            TensorBuffer.createFixedSize(shape, dType)
        interpreter.run(tensorImage.buffer, probabilityBuffer.buffer)
        val probabilityProcessor = TensorProcessor.Builder()
            .add(NormalizeOp(0.0f, 255.0f)).build()
        val labelWithProbability = TensorLabel(
            labels, probabilityProcessor.process(probabilityBuffer)).mapWithFloatValue
        val result = labelWithProbability.maxBy { it.value }?.toPair()
        recognizedLabel = result?.first
        probability = result?.second
    }

    fun close() {
        interpreter.close()
    }
}