package com.example.plugins

import com.example.RPGConfigs
import com.example.configs
import io.ktor.serialization.kotlinx.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.util.reflect.*
import io.ktor.websocket.*
import kotlinx.coroutines.channels.consumeEach
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.time.Duration

val clients = mutableListOf<DefaultWebSocketServerSession>()

fun Application.configureSockets() {
    install(WebSockets) {
        contentConverter = KotlinxWebsocketSerializationConverter(Json)
        pingPeriod = Duration.ofSeconds(15)
        timeout = Duration.ofSeconds(15)
        maxFrameSize = Long.MAX_VALUE
        masking = false
    }

    routing {
        webSocket("/ws") {
            clients += this
            sendSerialized(configs)
            try {
                incoming.consumeEach {
                    sendSerialized(configs)
                }
            } finally {
                clients -= this
            }
        }
    }
}
