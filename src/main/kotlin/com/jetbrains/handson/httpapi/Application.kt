package com.jetbrains.handson.httpapi
import DataBase.UsersDataBase
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {

  install(ContentNegotiation){json()}

  // Probar KREDS

}
