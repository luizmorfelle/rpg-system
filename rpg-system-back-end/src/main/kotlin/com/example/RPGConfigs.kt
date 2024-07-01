package com.example

import com.example.model.Scenario
import com.example.model.Enemy
import com.example.model.Music
import kotlinx.serialization.Serializable

@Serializable
data class RPGConfigs(
    var scenario: Scenario,
    var music: Music,
    var enemies: List<Enemy>,
)