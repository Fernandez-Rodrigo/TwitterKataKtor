package com.jetbrains.handson.httpapi
import APIs.TwitAPI
import APIs.UserAPI
import Context.Context
import com.jetbrains.handson.httpapi.Routes.registerUserRoutes
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

    val userApi = UserAPI()
    val twitAPI = TwitAPI()

    fun Application.module() {

      install(ContentNegotiation){json()}

      registerUserRoutes(userApi, twitAPI)

    }
