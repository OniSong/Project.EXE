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
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        ).apply {
            gravity = Gravity.BOTTOM or Gravity.END
        }

        overlayView = LayoutInflater.from(this).inflate(R.layout.overlay_fait, null)
        val sceneView = overlayView?.findViewById<SceneView>(R.id.sceneView)

        // If a VRM path is set in Settings, load it into the 3D scene
        config.vrmPath?.let { path ->
            sceneView?.loadModelAsync(path) {
                // Model Loaded: Trigger "Wake Up" Animation
            }
        }

        windowManager.addView(overlayView, params)
    }

    override fun onDestroy() {
        super.onDestroy()
        overlayView?.let { windowManager.removeView(it) }
    }
}
