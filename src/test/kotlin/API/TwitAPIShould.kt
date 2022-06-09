package API

import APIs.TwitAPI
import APIs.UserAPI
import Context.Context
import DataClasses.TwitData
import DataClasses.UserData
import Enum.ResponseEnum
import Interfaces.ITwitService
import Interfaces.IUserRepository
import Model.User
import com.github.fppt.jedismock.RedisServer
import io.ktor.http.*
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import redis.clients.jedis.JedisPooled
import kotlin.test.assertEquals

class TwitAPIShould {
    var server = RedisServer
        .newRedisServer()
        .start()

    private val jediServer = JedisPooled(server.host, server.bindPort)
    private val context = Context(jediServer)
    private val twitAPI = TwitAPI(context)
    private val userAPI = UserAPI(context)

    @Test
    fun `Return success when twit`(){

        userAPI.RegisterUserResponse(UserData("Rodrigo", "Fernandez", "Rodri"))
        val twitData = TwitData("Rodri", "Mensaje de prueba")

        val response = twitAPI.TwitResponse(twitData)

        assertEquals(HttpStatusCode.OK, response.response)
    }

    @Test
    fun `Return correct message on getting twits action`(){

        userAPI.RegisterUserResponse(UserData("Rodrigo", "Fernandez", "Rodri"))

        val response = twitAPI.GetTwitsResponse("Rodri")
        val failResponse = twitAPI.GetTwitsResponse("NonExistingUser")
        assertEquals(HttpStatusCode.Found, response.first.response)
        assertEquals(HttpStatusCode.BadRequest, failResponse.first.response)
    }


}