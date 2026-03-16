package com.onisong.projectexe.core

import android.app.Service
import android.content.Intent
import android.os.IBinder
import io.github.sceneview.SceneView
import io.github.sceneview.node.ModelNode

class OverlayService : Service() {
    private lateinit var sceneView: SceneView

    override fun onBind(intent: Intent?): IBinder? = null

    fun loadModel() {
        // Updated syntax for SceneView 0.10.0+
        val modelNode = sceneView.modelLoader.loadModel("models/fait.glb")
        modelNode?.let {
            sceneView.addChild(it)
        }
    }
}
