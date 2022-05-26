package com.jetbrains.handson.httpapi.Routes
import Context.Context
import DataClasses.FollowData
import DataClasses.UserData
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userRouting(context : Context) {

    route("/GetUser") {

        get ("{nickName}"){
            val id = call.parameters["nickName"] ?: return@get call.respondText(

                "Missing or malformed nickName",

                status = HttpStatusCode.BadRequest)

           val user = context.GetUserDataInstance().ExecuteGetUserData(id)

            call.respond("Nombre: " + user.name + " Apellido: " + user.lastName + " Nickname: " + user.nickName)
        }
    }

    route("/RegisterUser") {
        post() {
           val newUserData = call.receive<UserData>()

            context.GetRegisterUserInstance().ExecuteRegister(newUserData)

            call.respondText ("El usuario ${newUserData.nickName} a sido regstrado correctamente" , status = HttpStatusCode.Created)
        }
    }

    route("/EditUserData"){

            post {
                val newUserData = call.receive<UserData>()

                context.GetEditUserDataInstance().ExecuteChangeName(newUserData)

                call.respondText ("El usuario ${newUserData.nickName} a sido modificado correctamente" , status = HttpStatusCode.OK)
            }
    }

    route("/Follow"){

        post {
            val usersData = call.receive<FollowData>()

            context.GetFollowUserDataInstance().ExecuteFollow(usersData.userNickname, usersData.userToFollowNickname)

            call.respondText ("El usuario ${usersData.userNickname} sigue a ${usersData.userToFollowNickname}" , status = HttpStatusCode.OK)

        }
    }

}

fun Application.registerUserRoutes(context: Context) {

    routing {

        userRouting(context)

    }

}