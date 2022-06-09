package com.jetbrains.handson.httpapi.Routes
import APIs.TwitAPI
import APIs.UserAPI
import Context.Context
import DataClasses.FollowData
import DataClasses.NickNameData
import DataClasses.TwitData
import DataClasses.UserData
import com.google.gson.Gson
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userRouting(userAPI : UserAPI, twitAPI : TwitAPI) {

    route("/user") {


        get("/") {

            call.respondText("Hello, world!", status = HttpStatusCode.Created)
        }

        get ("{nickName}") {
            val id = call.parameters["nickName"] ?: return@get call.respondText(

                "Missing or malformed nickName",

                status = HttpStatusCode.BadRequest)

            call.respondText (userAPI.GetUserDataResponse(id).second.nickName, status = HttpStatusCode.OK )
        }

        post("/RegisterUser") {

            val newUserData = call.receive<UserData>()
            val response = userAPI.RegisterUserResponse(newUserData)

            call.respondText (response.second, status = response.first.response)
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

            val twitData = call.receive<TwitData>()
            val response = twitAPI.TwitResponse(twitData)

            call.respondText(response.second, status = response.first.response)
        }


    }

    route("/Twits"){

        get ("{nickName}"){
            val id = call.parameters["nickName"] ?: return@get call.respondText(

                "Missing or malformed nickName",

                status = HttpStatusCode.BadRequest)
            val response = twitAPI.GetTwitsResponse(id)

            call.respondText(Gson().toJson(response.second), status = response.first.response)
        }

    }

    route("/GetFollowers"){

        get("{nickName}") {

            val id = call.parameters["nickName"] ?: return@get call.respondText(

                "Missing or malformed nickName",

                status = HttpStatusCode.BadRequest)
            val response = userAPI.CheckFollowersResponse(id)
                 call.respondText(Gson().toJson(response.second) , status = response.first.response)

        }

     }


    }



fun Application.registerUserRoutes(userAPI: UserAPI, twitAPI: TwitAPI) {

    routing {

        userRouting(userAPI, twitAPI)

    }

}