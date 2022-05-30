package APIs

import Context.Context
import DataClasses.FollowData
import DataClasses.UserData
import Enum.ResponseEnum
import com.github.fppt.jedismock.RedisServer
import redis.clients.jedis.JedisPooled

class UserAPI_FAKE () {

    private var serverFAKE = RedisServer
        .newRedisServer()
        .start()

    private val jediServer = JedisPooled(serverFAKE.host, serverFAKE.bindPort)
    
    private val context = Context(jediServer)

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
        val response = context.GetFollowUserDataInstance().ExecuteFollow(usersData.userNickname, usersData.userToFollowNickname)
        return if ( response == ResponseEnum.DUPLICATED){

            Pair(ResponseEnum.DUPLICATED, "Ya sigues a este usuario")
        }else if(response == ResponseEnum.FOUND){

            Pair(ResponseEnum.FOUND, "Ahora sigues a "+usersData.userToFollowNickname)
        }else {

            Pair(response, "Usuario no encontrado")
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