package Routes

import APIs.TwitAPI
import APIs.UserAPI
import com.jetbrains.handson.httpapi.Routes.registerUserRoutes
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.json.JSONObject
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import kotlin.test.assertEquals
/*
class UserRoutesShould {

    private val twitAPI : TwitAPI = mock()
    private val userAPI : UserAPI = mock()
    @Test
    fun `Get user info by nickname`() = testApplication {this.application { registerUserRoutes(userAPI, twitAPI) }
       /* val json = JSONObject()
        json.put("name", "Usuario")
        json.put("lastName", "Usuario")
        json.put("nickName", "Rodri")

        val response = client.get("/user/Data"){
            contentType(ContentType.Application.Json)
            setBody(json.toString())
        }

         assertEquals(HttpStatusCode.Found, response.status)

        */
        val json = JSONObject()
        json.put("nickName", "Rodri")

         client.post("/user/Data"){

            contentType(ContentType.Application.Json)
            setBody(json.toString())

        }

        verify(userAPI.GetUserDataResponse("Rodri"))
    }

    @Test
    fun `Call register user action and return correct message`() = testApplication{

        val json = JSONObject()
        json.put("name", "Usuario")
        json.put("lastName", "Usuario")
        json.put("nickName", "Usuario")


        val response = client.post("/user/RegisterUser"){
            contentType(ContentType.Application.Json)
            setBody(json.toString())

        }

        assertEquals(HttpStatusCode.Created, response.status)
    }

    @Test
    fun `Allow to edit user name and last name`() = testApplication{
        val json = JSONObject()
        json.put("name", "Rodolfo")
        json.put("lastName", "Gomez")
        json.put("nickName", "Rodri")


        val response = client.post("/user/EditInfo"){
            contentType(ContentType.Application.Json)
            setBody(json.toString())

        }

        assertEquals(HttpStatusCode.OK, response.status)
    }

    @Test
    fun `Let one user follow another`() = testApplication {
        val json = JSONObject()
        json.put("userNickname", "Rodri")
        json.put("userToFollowNickname", "Albert")


        val response = client.post("/user/Follow"){
            contentType(ContentType.Application.Json)
            setBody(json.toString())
        }

        assertEquals(HttpStatusCode.Found, response.status)
    }

    @Test
    fun `Get followers list`() = testApplication {

        val response = client.get("/GetFollowers/Rodri"){
            url{

                protocol = URLProtocol.HTTP
            }

        }

        assertEquals(HttpStatusCode.OK, response.status)
    }

    @Test
    fun `Receive the correct response on twit`() = testApplication{

        val json = JSONObject()
        json.put("nickName", "Rodri")
        json.put("message", "Twit de prueba")

        val response = client.post("/user/WriteTwit"){
            contentType(ContentType.Application.Json)
            setBody(json.toString())
        }

        assertEquals(HttpStatusCode.OK, response.status)
    }

    @Test
    fun `Receive the correct response on Get twit`() = testApplication {
        val json = JSONObject()
        json.put("nickName", "Rodri")

        val response = client.post("/user/GetTwits"){
            contentType(ContentType.Application.Json)
            setBody(json.toString())
        }

        assertEquals(HttpStatusCode.OK, response.status)

    }

 }





    //region Private Methods

    //endregion




 */



