package Infraestructure

import Interfaces.ITwitRepository
import redis.clients.jedis.JedisPooled

class TwitRepository (val server : JedisPooled): ITwitRepository {


    override fun Twit(nickName: String, message : String) {

        server.lpush(nickName+"Twits", message)

    }

    override fun GetTwits(nickName: String): List<String> {
        return server.lrange(nickName+"Twits", 0,-1)
    }

}