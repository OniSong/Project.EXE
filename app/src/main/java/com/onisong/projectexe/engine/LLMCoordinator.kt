package com.onisong.projectexe.engine

interface LLMProvider {
    suspend fun generateResponse(prompt: String): String
}

class DualLLMWorkflow(
    val brain: LLMProvider,
    val persona: LLMProvider
) {
    suspend fun processNode(nodeId: String, input: String): String {
        // This is where the 45-node traversal logic will live
        // Brain decides the path, Persona decides the words
        return "Processing node $nodeId"
    }
}
