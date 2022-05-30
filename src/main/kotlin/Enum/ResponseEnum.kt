package Enum

import io.ktor.http.*

enum class ResponseEnum(val response : HttpStatusCode) {

    SUCCESS(response = HttpStatusCode.OK),
    FAILURE(response = HttpStatusCode.BadRequest),
    CREATED(response = HttpStatusCode.Created),
    DUPLICATED(response = HttpStatusCode.NotAcceptable),
    FOUND(response = HttpStatusCode.Found)
}