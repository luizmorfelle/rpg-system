package com.example.plugins

import com.example.configs
import com.example.model.Enemy
import com.example.model.Music
import com.example.model.Scenario
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*

fun Application.configureRouting() {
    routing {
        staticResources("static", "static")
        get("/") {
            call.respondText("Hello World!")
        }
        post("/change-scenario") {
            val scenario = call.receive<Scenario>()
            configs.scenario = scenario
            call.respondText("Scenario changed to: ${scenario.name}!")
            sendData()
        }
        post("/change-music") {
            val music = call.receive<Music>()
            configs.music = music
            call.respondText("Music changed to: ${music.name}!")
            sendData()
        }
        post("/add-enemies") {
            val enemies = call.receive<List<Enemy>>()
            configs.enemies += enemies
            call.respondText("Enemies added: ${enemies.size}!")
            sendData()
        }
    }
}

suspend fun sendData() {
    clients.forEach {
        it.sendSerialized(configs)
    }
}
