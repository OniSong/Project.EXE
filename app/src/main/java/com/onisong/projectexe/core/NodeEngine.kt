package com.onisong.projectexe.core

import android.content.Context
import kotlinx.coroutines.flow.MutableStateFlow

class NodeEngine(context: Context) {
    private val config = ConfigManager(context)
    private val avatarManager = AvatarManager(context)
    private val sensorGate = SensorGate(context)
    val currentNode = MutableStateFlow(0)

    suspend fun executeCycle(input: String): String {
        // NODES 1-10: Perception & Input Sanitization
        currentNode.value = 1
        
        // NODES 11-20: The Vault (Memory Retrieval)
        currentNode.value = 11
        val memoryContext = "User is building a system agent in Barstow."

        // NODES 21-30: Sensory Gate (Environment Perception)
        currentNode.value = 21
        val systemContext = sensorGate.getSystemContext()
        
        // NODES 31-40: Creative Forge (Persona Application)
        currentNode.value = 31
        if (avatarManager.isAvatarLoaded()) {
            val state = if (sensorGate.isCharging()) "Energized" else "Gothic"
            avatarManager.updateAnimation(state)
        }

        // NODES 41-45: Validation & Output
        currentNode.value = 45
        return "Fait: [System: ${systemContext}] I see the architecture evolving, Snow. Memory confirms: ${memoryContext}"
    }
}
