package com.onisong.projectexe.engine

interface LLMProvider {
    suspend fun generate(prompt: String): String
    suspend fun generate(prompt: String, context: String): String
}
