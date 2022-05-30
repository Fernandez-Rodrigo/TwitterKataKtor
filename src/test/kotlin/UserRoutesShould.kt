import APIs.UserAPI
import com.jetbrains.handson.httpapi.Routes.registerUserRoutes
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.json.JSONObject
import org.junit.Test
import org.mockito.kotlin.mock
import kotlin.test.assertEquals

class UserRoutesShould {


    private val userAPI : UserAPI = mock()
    @Test
    fun `Get user info by nickname`() = testApplication {this.application { registerUserRoutes(userAPI)}
        val json = JSONObject()
        json.put("name", "Usuario")
        json.put("lastName", "Usuario")
        json.put("nickName", "Rodri")

        val response = client.post("/user/Data"){
            contentType(ContentType.Application.Json)
            setBody(json.toString())
        }

         assertEquals(HttpStatusCode.Found, response.status)
    }

    @Test
    fun `Call register user action and return correct message`() = testApplication{this.application { registerUserRoutes(userAPI)}

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
        json.put("userToFollowNickname", "Rodri")

        val response = client.post("/user/Follow"){
            contentType(ContentType.Application.Json)
            setBody(json.toString())
        }

        assertEquals(HttpStatusCode.Found, response.status)
    }

    @Test
    fun `Get followers list`() = testApplication {

        val response = client.get("/GetFollowres/Rodri")

        assertEquals(HttpStatusCode.OK, response.status)
    }


    }





    //region Private Methods

    //endregion






