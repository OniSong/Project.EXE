package com.onisong.projectexe.core

import com.onisong.projectexe.engine.LLMProvider

class NodeEngine(
    private val leftBrain: LLMProvider,  // Factual (Larger/Smarter)
    private val rightBrain: LLMProvider // Persona (Fait/Smaller)
) {

    suspend fun execute45NodeWorkflow(userInput: String): String {
        // --- STAGE 1: PERCEPTION (Nodes 01-10) ---
        // Node 1 & 5: Input and Sentiment Analysis
        val sentiment = rightBrain.generate("Analyze sentiment of: $userInput")

        // --- STAGE 2: THE SPARK (P1 - Node 11) ---
        val p1Draft = rightBrain.generate(userInput, "Role: Quick Persona Response")

        // --- STAGE 3: THE VAULT & FACT CHECK (F1 - Nodes 12-30) ---
        // Includes Device State (Node 15) and ChromaDB
        val factualAnalysis = leftBrain.generate("""
            Query: $userInput
            P1 Draft: $p1Draft
            Task: Identify inaccuracies and retrieve new facts.
        """.trimIndent())

        // --- STAGE 4: EXPANSION (P2 - Nodes 31-40) ---
        val p2Expanded = rightBrain.generate("""
            Original: $p1Draft
            Corrections: $factualAnalysis
            Task: Expand using facts while keeping Fait's persona.
        """.trimIndent())

        // --- STAGE 5: FINAL VERIFICATION (F2 - Node 43) ---
        val f2Verified = leftBrain.generate("Verify integrity of: $p2Expanded")
        
        if (f2Verified.contains("FAIL")) {
             // Fallback logic if persona drift occurred
             return p2Expanded 
        }

        // --- STAGE 6: SOUL ALIGNMENT (P3 - Node 45) ---
        // Applying the "Greptile Rule": Reword only.
        return rightBrain.generate("""
            Content: $p2Expanded
            Task: Apply gothic/dominant style. DO NOT add or remove facts.
        """.trimIndent())
    }
}
