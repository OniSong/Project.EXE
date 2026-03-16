package com.onisong.projectexe.core
import android.content.Context

class ConfigManager(context: Context) {
    private val prefs = context.getSharedPreferences("FaitConfig", Context.MODE_PRIVATE)
    
    // Local File Paths (GGUF/VRM)
    var leftGGUF: String? get() = prefs.getString("l_gguf", null); set(v) = prefs.edit().putString("l_gguf", v).apply()
    var rightGGUF: String? get() = prefs.getString("r_gguf", null); set(v) = prefs.edit().putString("r_gguf", v).apply()
    var vrmPath: String? get() = prefs.getString("vrm", null); set(v) = prefs.edit().putString("vrm", v).apply()
    
    // API Configurations
    var leftAPI: String? get() = prefs.getString("l_api", ""); set(v) = prefs.edit().putString("l_api", v).apply()
    var rightAPI: String? get() = prefs.getString("r_api", ""); set(v) = prefs.edit().putString("r_api", v).apply()
    var useCloud: Boolean get() = prefs.getBoolean("cloud", false); set(v) = prefs.edit().putBoolean("cloud", v).apply()
}
