package com.onisong.projectexe
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.onisong.projectexe.core.ConfigManager
import com.onisong.projectexe.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    private lateinit var config: ConfigManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        config = ConfigManager(this)
        binding.swCloud.isChecked = config.useCloud
        binding.swCloud.setOnCheckedChangeListener { _, isC -> config.useCloud = isC }
    }
}
