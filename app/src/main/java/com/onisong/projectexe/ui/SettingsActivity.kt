package com.onisong.projectexe.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.onisong.projectexe.core.ConfigManager
import com.onisong.projectexe.core.OverlayService
import com.onisong.projectexe.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    private lateinit var config: ConfigManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        config = ConfigManager(this)

        // Ensure the system can draw the 3D overlay
        if (!Settings.canDrawOverlays(this)) {
            val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:$packageName"))
            startActivityForResult(intent, 101)
            Toast.makeText(this, "Enable 'Display over other apps' to manifest Fait.", Toast.LENGTH_LONG).show()
        }

        binding.swCloud.isChecked = config.useCloud
        binding.swCloud.setOnCheckedChangeListener { _, isChecked -> 
            config.useCloud = isChecked 
            if (isChecked && Settings.canDrawOverlays(this)) {
                val serviceIntent = Intent(this, OverlayService::class.java)
                startForegroundService(serviceIntent)
            }
        }
    }
}
