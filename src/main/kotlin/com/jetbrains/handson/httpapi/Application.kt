package com.jetbrains.handson.httpapi
import Context.Context
import DataBase.UsersDataBase
import com.jetbrains.handson.httpapi.Routes.registerUserRoutes
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

    val context = Context()

    fun Application.module() {

      install(ContentNegotiation){json()}

      registerUserRoutes(context)

    }
