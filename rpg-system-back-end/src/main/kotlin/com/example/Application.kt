package com.example

import com.example.model.FOREST
import com.example.model.PANTANEIRO
import com.example.plugins.*
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

val configs: RPGConfigs = RPGConfigs(FOREST, listMusics[0], mutableListOf(PANTANEIRO))

fun Application.module() {
    configureSockets()
    configureRouting()
}
