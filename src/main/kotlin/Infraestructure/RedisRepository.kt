package Infraestructure
import Interfaces.IUserRepository
import redis.clients.jedis.JedisPool

import Model.User
import redis.clients.jedis.JedisPooled

class RedisRepository (val server : JedisPooled) : IUserRepository{

    var userMap = mutableMapOf<String, User>()

    var pool = JedisPool("127.0.0.1", 6379)

    override fun AddUser(newUser: User) {

        server.hset(newUser.nickName, "Name", newUser.name)
        server.hset(newUser.nickName, "LastName", newUser.lastName)

        userMap[newUser.nickName] = newUser

    }

    override fun GetUser(nickName: String): User? {

        return userMap[nickName]

    }

    override fun EditUser(user: User, newName: String, newLastName: String) {
        TODO("Not yet implemented")
    }

    override fun ExistUser(nickName: String): Boolean {
        TODO("Not yet implemented")
    }

    fun CheckUserExist(nickName : String) : Boolean{

        return server.exists(nickName)
    }

}