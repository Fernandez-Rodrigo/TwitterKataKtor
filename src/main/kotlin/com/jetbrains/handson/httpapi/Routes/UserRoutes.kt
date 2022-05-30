package com.jetbrains.handson.httpapi.Routes
import APIs.UserAPI
import Context.Context
import DataClasses.FollowData
import DataClasses.UserData
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userRouting(userAPI : UserAPI) {

    route("/user") {

        post("/Data") {
           /*val id = call.parameters["nickName"] ?: return@get call.respondText(

              "Missing or malformed nickName",

                status = HttpStatusCode.BadRequest
            )

            */
            val newUserData = call.receive<UserData>()
            call.respond(userAPI.GetUserDataResponse(newUserData.nickName).first.response)
        }

        post("/RegisterUser") {

            val newUserData = call.receive<UserData>()

            call.respond(userAPI.RegisterUserResponse(newUserData).first.response)
        }


        post("/EditInfo") {
            val newUserData = call.receive<UserData>()

            val response = userAPI.EditUserDataResponse(newUserData)
            call.respondText(
                response.second,
                status = response.first.response
            )
        }

        post("/Follow") {

            val usersData = call.receive<FollowData>()
            val response = userAPI.FollowUserResponse(usersData)
            call.respondText(response.second,
                status = response.first.response
            )

        }


        post("/WriteTwit") {

            //    context.GetTwitInstance().ExecuteTwit(twitMessage)

            call.respond(HttpStatusCode.OK)
        }
    }


    route("/GetFollowres"){

        get("{nickName}") {

            val id = call.parameters["nickName"] ?: return@get call.respondText(

                "Missing or malformed nickName",

                status = HttpStatusCode.BadRequest)
            val response = userAPI.CheckFollowersResponse(id).first.response
                 call.respondText("Text", status = response)
        }

     }


    }



fun Application.registerUserRoutes(userAPI: UserAPI) {

    routing {

        userRouting(userAPI)

    }

}