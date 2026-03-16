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
import com.onisong.projectexe.R

class OverlayService : Service() {
    private lateinit var windowManager: WindowManager
    private var overlayView: View? = null
    private val config by lazy { ConfigManager(this) }

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
                // Fix: Passing view.engine to satisfy the new ModelNode constructor
                val modelNode = ModelNode(view.engine).apply {
                    loadModelAsync(
                        context = this@OverlayService,
                        lifecycle = null,
                        modelInstance = path,
                        autoAnimate = true
                    )
                }
                view.addChild(modelNode)
            }
        }

        windowManager.addView(overlayView, params)
    }

    override fun onDestroy() {
        super.onDestroy()
        overlayView?.let { windowManager.removeView(it) }
    }
}
