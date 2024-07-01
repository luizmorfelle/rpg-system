package com.example

import com.example.model.BaseModel
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*

val client = HttpClient(CIO)
var running = true

enum class ApplicationState {
    MAIN_MENU, COMBAT
}

var state: ApplicationState = ApplicationState.MAIN_MENU

suspend fun main() {
    while (running) {
        when (state) {
            ApplicationState.MAIN_MENU -> mainMenu()
            ApplicationState.COMBAT -> combat()
        }
    }
    client.close()
}

suspend fun mainMenu() {
    clearTerminal()
    println(
        """
        1. Mudar cenário
        2. Mudar música
        3. Entrar em combate
        0. Sair
    """.trimIndent()
    )
    val option = readln().toInt()

    when (option) {
        1 -> changeScenario();
        2 -> changeMusic();
        3 -> combat();
        0 -> running = false
    }
}

suspend fun combat() {
    clearTerminal()
    state = ApplicationState.COMBAT
    println(
        """
        1. Adicionar inimigo
        2. Dar dano
        3. Remover inimigo
        0. Retornar
    """.trimIndent()
    )
    val option = readln().toInt()
    when (option) {
        1 -> addEnemy();
        0 -> state = ApplicationState.MAIN_MENU
    }
}

suspend fun addEnemy() {
    val enemy = printOptionsAndGet(listEnemies) ?: return

    println("Quantos inimigos?")
    val qtdOption = readln().toInt()

    sendToWebService("add-enemies", null, List(qtdOption) { enemy })

}

fun printOptionsAndGet(models: List<BaseModel>): BaseModel? {
    clearTerminal()
    println(models.joinToString("\n", transform = { "${it.id}. ${it.name}" }))
    println("0. Return")

    val option = readln().toInt()
    return models.find { it.id == option };
}

suspend fun changeMusic() {
    val result = printOptionsAndGet(listMusics) ?: return
    sendToWebService("change-music", result)
}

suspend fun changeScenario() {
    val result = printOptionsAndGet(listScenarios) ?: return
    sendToWebService("change-scenario", result)
}

fun clearTerminal() {
    print("\u001b[H\u001b[2J")
    System.out.flush()
}

suspend fun sendToWebService(endpoint: String, body: BaseModel?, listBody: List<BaseModel>? = null) {
    client.post("http://localhost:8080/$endpoint") {
        setBody(body ?: listBody)
    }
}
