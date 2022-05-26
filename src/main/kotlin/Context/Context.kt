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

class Context {

    private val jediPool = JedisPooled("127.0.0.1", 6379)
    private val memoryRepository = MemoryRepository()
    private val redisRepository = RedisRepository(jediPool)
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

    fun GetCheckFollowersInstanc() : CheckFollowers{

        return CheckFollowers(userService)
    }

    fun GetRegisterUserInstance() : RegisterUser{

        return RegisterUser(userService)
    }

}