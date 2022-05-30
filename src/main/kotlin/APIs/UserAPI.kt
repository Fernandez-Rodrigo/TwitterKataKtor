package APIs

import Context.Context
import DataClasses.FollowData
import DataClasses.UserData
import Enum.ResponseEnum
import io.ktor.http.*
import redis.clients.jedis.JedisPooled

open class UserAPI (server : JedisPooled = JedisPooled("127.0.0.1", 6379)) {

    private val context = Context(server)

    fun CheckFollowersResponse(nickName : String) : Pair<ResponseEnum, MutableList<String>> {

      return context.GetCheckFollowersInstance().ExecuteCheck(nickName)

    }

    fun EditUserDataResponse(newUserData : UserData) : Pair<ResponseEnum, String> {
        val response = context.GetEditUserDataInstance().ExecuteChangeName(newUserData)
       return if(response == ResponseEnum.SUCCESS){

           Pair(response, "Los cambios se realizaron satisfactoriamente")
       } else{

           Pair(response, "No se encontró dicho usuario")
       }

    }

    fun FollowUserResponse(usersData : FollowData) :  Pair<ResponseEnum, String> {
        return when (val response = context.GetFollowUserDataInstance().ExecuteFollow(usersData.userNickname, usersData.userToFollowNickname)) {
            ResponseEnum.DUPLICATED -> {

                Pair(ResponseEnum.DUPLICATED, "Ya sigues a este usuario")
            }
            ResponseEnum.FOUND -> {

                Pair(ResponseEnum.FOUND, "Ahora sigues a "+usersData.userToFollowNickname)
            }
            else -> {

                Pair(response, "Usuario no encontrado")
            }
        }

    }

    fun GetUserDataResponse(nickName : String) : Pair<ResponseEnum, UserData> {

       return context.GetUserDataInstance().ExecuteGetUserData(nickName)
    }

    fun RegisterUserResponse(userData : UserData) : Pair<ResponseEnum, String> {
        val response = context.GetRegisterUserInstance().ExecuteRegister(userData)

        return if(response == ResponseEnum.DUPLICATED){

            Pair(response, "Nickname ya utilizado")

        }else{

            Pair(response, "El usuario fue registrado correctamente")
        }

    }

}