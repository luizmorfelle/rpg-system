package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class Music(override val id: Int, override val name: String) : BaseModel
