package com.example

import com.example.model.*

val listScenarios: List<BaseModel> = listOf(FOREST, SWAMP)

val listMusics: List<Music> = listScenarios.map { Music(it.id, it.name) }

val listEnemies = listOf(PANTANEIRO, TROLL)
