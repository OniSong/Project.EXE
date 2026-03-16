package com.onisong.projectexe.core
import android.app.Service
import android.content.Intent
import android.graphics.PixelFormat
import android.os.IBinder
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import com.onisong.projectexe.R

class OverlayService : Service() {
    private lateinit var windowManager: WindowManager
    private lateinit var overlayView: View
    override fun onBind(intent: Intent?): IBinder? = null
    override fun onCreate() {
        super.onCreate()
        windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, PixelFormat.TRANSLUCENT
        ).apply { gravity = Gravity.BOTTOM or Gravity.END; x = 0; y = 100 }
        overlayView = View(this).apply { setBackgroundColor(0xFF00FF00.toInt()) } // Fait Placeholder
        windowManager.addView(overlayView, params)
    }
    override fun onDestroy() {
        super.onDestroy()
        windowManager.removeView(overlayView)
    }
}
