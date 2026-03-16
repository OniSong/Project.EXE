package com.onisong.projectexe.core

import android.app.Service
import android.content.Intent
import android.os.IBinder
import io.github.sceneview.SceneView
import io.github.sceneview.node.ModelNode

class OverlayService : Service() {
    private var modelLoader: Any? = null
    private lateinit var modelLoader: Any // Replace Any with your 3D engine type
    private lateinit var sceneView: SceneView

    override fun onBind(intent: Intent?): IBinder? = null

    fun loadModel() {
        // AGP 9.x / SceneView 2.x Syntax
        val modelNode = sceneView.modelLoader.loadModel("models/fait.glb")
        modelNode?.let {
            sceneView.addChild(it)
        }
    }
}
