package com.onisong.projectexe.core

import android.app.Service
import android.content.Intent
import android.graphics.PixelFormat
import android.os.IBinder
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import io.github.sceneview.SceneView
import io.github.sceneview.node.ModelNode
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import com.onisong.projectexe.R

class OverlayService : Service() {
    private lateinit var windowManager: WindowManager
    private var overlayView: View? = null
    private val config by lazy { ConfigManager(this) }
    
    // Establishing a coroutine scope tied directly to the Service lifecycle
    private val serviceJob = Job()
    private val serviceScope = CoroutineScope(Dispatchers.Main + serviceJob)

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
        
        val params = WindowManager.LayoutParams(
            250, 400,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        ).apply {
            gravity = Gravity.BOTTOM or Gravity.END
        }

        overlayView = LayoutInflater.from(this).inflate(R.layout.overlay_fait, null)
        val sceneView = overlayView?.findViewById<SceneView>(R.id.sceneView)

        sceneView?.let { view ->
            config.vrmPath?.let { path ->
                // The correct 0.10.0 logic: Launch a coroutine to build the instance, then attach it
                serviceScope.launch {
                    try {
                        val instance = view.modelLoader.createModelInstance(path)
                        if (instance != null) {
                            val modelNode = ModelNode(modelInstance = instance)
                            view.addChildNode(modelNode)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }

        windowManager.addView(overlayView, params)
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceJob.cancel() // Kill coroutines to prevent memory leaks when service dies
        overlayView?.let { windowManager.removeView(it) }
    }
}
