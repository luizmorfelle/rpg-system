package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class Enemy(override val id: Int, override val name: String, val image: String) : BaseModel

val PANTANEIRO: Enemy = Enemy(1, "Pantaneiro", "pantaneiro")
val TROLL: Enemy = Enemy(2, "Troll", "troll")