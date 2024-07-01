package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class Scenario(override val id: Int, override val name: String, val image: String) : BaseModel

val FOREST = Scenario(1, "Forest", "forest")
val SWAMP = Scenario(2, "Swamp", "swamp")