package com.onisong.projectexe.core

import android.content.Context
import kotlinx.coroutines.flow.MutableStateFlow

class NodeEngine(context: Context) {
    private val config = ConfigManager(context)
    private val avatarManager = AvatarManager(context)
    private val sensorGate = SensorGate(context)
    val currentNode = MutableStateFlow(0)

    suspend fun executeCycle(input: String): String {
        // Node 1: Perception
        currentNode.value = 1
        
        // Node 15: Environmental Awareness
        val systemContext = sensorGate.getSystemContext()
        
        // Node 30: Avatar Animation Trigger
        if (avatarManager.isAvatarLoaded()) {
            val animationState = if (sensorGate.isCharging()) "Energized" else "Gothic"
            avatarManager.updateAnimation(animationState)
        }
        
        currentNode.value = 45
        return "Fait: ${systemContext}. I am observing the void with you."
    }
}
