package Routes
import Infraestructure.RedisRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.userRouting() {

    route("/user") {

        get {

        }

        get("{nickName}") {
            val nickName = call.parameters["nickName"] ?: return@get call.respondText(

                "Missing or malformed nickName",

                status = HttpStatusCode.BadRequest)

        }

        post {

        }

        delete("{nickName}") {

        }

    }

}