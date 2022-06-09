package Context

import Action.*
import Infraestructure.MemoryRepository
import Infraestructure.RedisRepository
import Infraestructure.TwitRepository
import RegisterUser
import Services.TwitService
import Services.UserService
import redis.clients.jedis.JedisPooled

class Context(server : JedisPooled = JedisPooled("127.0.0.1", 6379)) {


    private val memoryRepository = MemoryRepository()
    private val redisRepository = RedisRepository(server)
    private val twitRepository = TwitRepository(server)
    private val userService = UserService(redisRepository)
    private val twitService = TwitService(twitRepository)

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

    fun TwitInstance() : Twit {

        return Twit(twitService)
    }

    fun GetTwitsInstance() : GetTwits{

        return GetTwits(twitService)

    }


}