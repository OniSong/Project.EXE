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

        checkOverlayPermission()

        binding.swCloud.isChecked = config.useCloud
        binding.swCloud.setOnCheckedChangeListener { _, isChecked -> 
            config.useCloud = isChecked 
            if (isChecked) startFaitService()
        }
    }

    private fun checkOverlayPermission() {
        if (!Settings.canDrawOverlays(this)) {
            val intent = Intent(
                Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:$packageName")
            )
            startActivityForResult(intent, 123)
            Toast.makeText(this, "Please enable 'Display over other apps' for Fait", Toast.LENGTH_LONG).show()
        }
    }

    private fun startFaitService() {
        if (Settings.canDrawOverlays(this)) {
            val intent = Intent(this, OverlayService::class.java)
            startForegroundService(intent)
        }
    }
}
