package Context

import Action.CheckFollowers
import Action.EditUserData
import Action.FollowUsers
import Action.GetUserData
import Infraestructure.MemoryRepository
import Infraestructure.RedisRepository
import RegisterUser
import Services.UserService
import redis.clients.jedis.JedisPooled

class Context(server : JedisPooled = JedisPooled("127.0.0.1", 6379)) {


    private val memoryRepository = MemoryRepository()
    private val redisRepository = RedisRepository(server)
    private val userService = UserService(redisRepository)



    fun GetUserDataInstance() : GetUserData{

        return GetUserData(userService)
    }

    fun GetEditUserDataInstance() : EditUserData{

        return EditUserData(userService)
    }

    fun GetFollowUserDataInstance() : FollowUsers{

        return FollowUsers(userService)
    }

    fun GetCheckFollowersInstance() : CheckFollowers{

        return CheckFollowers(userService)
    }

    fun GetRegisterUserInstance() : RegisterUser{

        return RegisterUser(userService)
    }


}