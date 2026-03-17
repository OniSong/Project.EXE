package com.onisong.projectexe.core

import android.app.Service
import android.content.Intent
import android.os.IBinder
import io.github.sceneview.SceneView
import io.github.sceneview.node.ModelNode

class OverlayService : Service() {
    private var sceneView: SceneView? = null

    override fun onBind(intent: Intent?): IBinder? = null

    fun loadModel() {
        val view = sceneView ?: return
        
        // We use the direct constructor identified in your error logs:
        // ModelNode(engine, modelGlbFileLocation, autoAnimate, ...)
        val modelNode = ModelNode(
            engine = view.engine,
            modelGlbFileLocation = "models/fait.glb",
            autoAnimate = true
        )
        
        view.addChild(modelNode)
    }
}
