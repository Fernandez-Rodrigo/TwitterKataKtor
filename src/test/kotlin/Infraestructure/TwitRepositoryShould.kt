package Infraestructure

import com.github.fppt.jedismock.RedisServer
import org.junit.Test
import redis.clients.jedis.JedisPooled
import kotlin.test.assertEquals

class TwitRepositoryShould {


    var server = RedisServer
        .newRedisServer()
        .start()
    val jediPool = JedisPooled(server.host, server.bindPort)

    @Test
    fun `Add twit to a list`(){
        val twitRepository = TwitRepository(jediPool)

        twitRepository.Twit("Usuario1", "Mensaje")

        assertEquals(1, twitRepository.server.lrange("Usuario1Twits", 0, -1).count())

    }

    @Test
    fun `Return List of twits`(){
        val twitRepository = TwitRepository(jediPool)

        val listOfTwits = twitRepository.GetTwits("Usuario1")

        assertEquals(listOf(), listOfTwits)
    }


}