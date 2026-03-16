package com.onisong.projectexe

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.onisong.projectexe.core.ConfigManager
import com.onisong.projectexe.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    private lateinit var config: ConfigManager

    private val filePicker = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        result.data?.data?.let { uri ->
            // In a full build, we would identify which button triggered this
            // and save to config. For now, this establishes the capability.
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        config = ConfigManager(this)

        binding.swCloud.isChecked = config.useCloud
        binding.swCloud.setOnCheckedChangeListener { _, isChecked -> config.useCloud = isChecked }
        
        binding.btnLeftGGUF.setOnClickListener { openPicker() }
        binding.btnRightGGUF.setOnClickListener { openPicker() }
        binding.btnVRM.setOnClickListener { openPicker() }
    }

    private fun openPicker() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "*/*"
        }
        filePicker.launch(intent)
    }
}
