package com.onisong.projectexe.core

import android.content.Context
import android.net.Uri

class AvatarManager(private val context: Context) {
    private val config = ConfigManager(context)

    fun getAvatarUri(): Uri? {
        return config.vrmPath?.let { Uri.parse(it) }
    }

    fun isAvatarLoaded(): Boolean {
        return !config.vrmPath.isNullOrEmpty()
    }

    // This is where we will later plug in the 3D rendering triggers
    fun updateAnimation(sentiment: String) {
        when (sentiment) {
            "Gothic" -> println("Triggering Shadow Animation")
            "Dark" -> println("Triggering Shatter Effect")
            else -> println("Idle Breathing")
        }
    }
}
