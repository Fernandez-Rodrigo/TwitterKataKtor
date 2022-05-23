package DataBase

import redis.clients.jedis.JedisPool


class UsersDataBase {

    var pool = JedisPool("127.0.0.1", 6379)

    fun Test(){
        pool.resource.use { jedis -> jedis.set("clientName", "Rodrigo") }
    }

    fun TestReturn():String{

        return pool.resource.use { jedis -> jedis.get("clientName")}
    }

}