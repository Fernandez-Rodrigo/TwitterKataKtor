package Infraestructure
import Interfaces.IUserRepository
import Model.User
import redis.clients.jedis.JedisPooled

class RedisRepository (val server : JedisPooled) : IUserRepository{

    override fun AddUser(newUser: User) {
        server.hset(newUser.nickName, "Name", newUser.name)
        server.hset(newUser.nickName, "LastName", newUser.lastName)
    }

    override fun GetUser(nickName: String): User? {
        return if(server.hget(nickName, "Name") == null || server.hget(nickName, "LastName") == null){
            null
        }else
            User(server.hget(nickName, "Name"), server.hget(nickName, "LastName"), nickName)
    }

    override fun EditUser(user: User, newName: String, newLastName: String) {
        server.hset(user.nickName, "Name", newName)
        server.hset(user.nickName, "LastName", newLastName)
    }

    override fun ExistUser(nickName: String): Boolean {
        return server.exists(nickName)
    }


    override fun FollowUser(userNickname : String, userToFollowNickname : String) : Boolean{

        return if(server.lrange(userNickname+"Follows", 0, -1).contains(userToFollowNickname)){
            false
        } else{
            server.rpush(userNickname+"Follows", userToFollowNickname)
            true
        }
    }

    override fun GetFollowersList(userNickname : String): MutableList<String> {

        return server.lrange(userNickname+"Follows",0,-1)
    }


}