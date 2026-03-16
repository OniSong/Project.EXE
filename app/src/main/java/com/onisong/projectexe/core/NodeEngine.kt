package com.onisong.projectexe.core

import android.content.Context
import kotlinx.coroutines.flow.MutableStateFlow

class NodeEngine(context: Context) {
    private val config = ConfigManager(context)
    private val avatarManager = AvatarManager(context)
    val currentNode = MutableStateFlow(0)

    suspend fun executeCycle(input: String): String {
        currentNode.value = 1
        
        // Node 15: Check if the 3D body is ready
        if (avatarManager.isAvatarLoaded()) {
            avatarManager.updateAnimation("Gothic")
        }
        
        currentNode.value = 45
        return "Fait: The void is stabilized. Your 3D vessel is prepared."
    }
}
