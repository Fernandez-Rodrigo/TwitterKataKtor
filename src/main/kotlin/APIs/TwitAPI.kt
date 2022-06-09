package APIs

import Context.Context
import DataClasses.TwitData
import Enum.ResponseEnum
import redis.clients.jedis.JedisPooled

open class TwitAPI (private val context : Context = Context(JedisPooled("127.0.0.1", 6379))) {



    fun TwitResponse(twitData : TwitData) : Pair<ResponseEnum, String> {

        val response = context.TwitInstance().ExecuteTwit(twitData)

        return if (context.GetUserDataInstance().ExecuteGetUserData(twitData.nickName).first == ResponseEnum.SUCCESS){
           Pair(response, "Twit enviado!")
        } else {
            Pair(ResponseEnum.FAILURE, "Usuario no encontrado")
        }


    }

    fun GetTwitsResponse(nickName : String) : Pair<ResponseEnum, List<String>>{

        return if (context.GetUserDataInstance().ExecuteGetUserData(nickName).first == ResponseEnum.SUCCESS){
        return context.GetTwitsInstance().ExecuteGetTwits(nickName)
        } else {
            Pair(ResponseEnum.FAILURE, listOf())
        }
    }

}