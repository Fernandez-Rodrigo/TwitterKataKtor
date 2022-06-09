package API

import APIs.UserAPI
import Context.Context
import DataClasses.FollowData
import DataClasses.UserData
import Enum.ResponseEnum
import com.github.fppt.jedismock.RedisServer
import io.ktor.http.*
import org.junit.Test
import redis.clients.jedis.JedisPooled
import kotlin.test.assertEquals

class UserAPIShould {
    var server = RedisServer
        .newRedisServer()
        .start()

    private val jediServer = JedisPooled(server.host, server.bindPort)
    private val context = Context(jediServer)
    private val userAPI = UserAPI(context)

    @Test
    fun `Return correct response when try to get an existing user`() {

        val userData = `Given a new user register`()

        val response = `When try to get the data of the user`()

        `Then expect the http found and user data`(response, userData)
    }



    @Test
    fun `Return failure response when try to get non-existent user`(){

        val response = `When try to get a non exist user data`()

        `Then expect bad request`(response)

    }


    @Test
    fun `Register user with correct response`(){

        val newUser = `Given new user data`()

        val response = `When try to register the new user`(newUser)

        `Then expect created response`(response)

    }

    @Test
    fun `Return duplicated response when try to register two users with same nickname`(){

        val newUser = UserData("Ricardo", "Ruben", "Richard")
        userAPI.RegisterUserResponse(newUser)
        val response = userAPI.RegisterUserResponse(newUser)

        assertEquals(HttpStatusCode.NotAcceptable, response.first.response)
    }

    @Test
    fun `Respond with correct message when follow user `(){

        val userData = UserData("Rodrigo", "Fernandez", "Rodri")
        val userToFollow = UserData("Alberto", "Perez", "Beto")

        val followData = FollowData(userData.nickName, userToFollow.nickName)
        userAPI.RegisterUserResponse(userData)
        userAPI.RegisterUserResponse(userToFollow)

        val response = userAPI.FollowUserResponse(followData)

        assertEquals(HttpStatusCode.Found, response.first.response)
    }

    @Test
    fun `Don't allow to follow twice the same user`(){

        val userData = UserData("Rodrigo", "Fernandez", "Rodri")
        val userToFollow = UserData("Alberto", "Perez", "Beto")
        val followData = FollowData(userData.nickName, userToFollow.nickName)

        userAPI.RegisterUserResponse(userData)
        userAPI.RegisterUserResponse(userToFollow)
        userAPI.FollowUserResponse(followData)
        val response = userAPI.FollowUserResponse(followData)

        assertEquals(HttpStatusCode.NotAcceptable, response.first.response)
    }

    @Test
    fun `Response with failure when one or mor of the users do not exist`(){

        val followData = FollowData("UsuarioA", "UsuarioB")

        val response = userAPI.FollowUserResponse(followData)

        assertEquals(HttpStatusCode.BadRequest, response.first.response)
    }

    @Test
    fun `Response with success when character exist on edit character data `(){
        val newUser = UserData("Ricardo", "Ruben", "Richard")
        val newData = UserData("Nicolas", "Messi", "Richard")
        userAPI.RegisterUserResponse(newUser)

        val response = userAPI.EditUserDataResponse(newData)

        assertEquals(HttpStatusCode.OK, response.first.response)
    }

    @Test
    fun `Response with failure when character do not exist on edit character data `(){
        val newData = UserData("Nicolas", "Messi", "Richard")

        val response = userAPI.EditUserDataResponse(newData)

        assertEquals(HttpStatusCode.BadRequest, response.first.response)
    }

    @Test
    fun `Return success response when trying to check the followers list of an existing user`(){
        val newUser = UserData("Ricardo", "Ruben", "Richard")

        userAPI.RegisterUserResponse(newUser)

        val response = userAPI.CheckFollowersResponse(newUser.nickName)

        assertEquals(HttpStatusCode.Found, response.first.response)
    }

    @Test
    fun `Return fail response when trying to check the followers list of an non existing user`(){

        val response = userAPI.CheckFollowersResponse("NonExisting")

        assertEquals(HttpStatusCode.BadRequest, response.first.response)
    }




    //region Private Methods

    private fun `Given a new user register`(): UserData {
        val userData = UserData("Rodrigo", "Fernandez", "Rodri")
        userAPI.RegisterUserResponse(userData)
        return userData
    }


    private fun `Given new user data`(): UserData {
        return UserData("Ricardo", "Ruben", "Richard")
    }


    private fun `When try to register the new user`(newUser: UserData): Pair<ResponseEnum, String> {
        return userAPI.RegisterUserResponse(newUser)
    }


    private fun `When try to get the data of the user`(): Pair<ResponseEnum, UserData> {
        return userAPI.GetUserDataResponse("Rodri")
    }

    private fun `When try to get a non exist user data`(): Pair<ResponseEnum, UserData> {
        return userAPI.GetUserDataResponse("NoExiste")
    }

    private fun `Then expect the http found and user data`(
        response: Pair<ResponseEnum, UserData>,
        userData: UserData
    ) {
        assertEquals(HttpStatusCode.Found, response.first.response)
        assertEquals(userData, response.second)
    }

    private fun `Then expect bad request`(response: Pair<ResponseEnum, UserData>) {
        assertEquals(HttpStatusCode.BadRequest, response.first.response)
    }


    private fun `Then expect created response`(response: Pair<ResponseEnum, String>) {
        assertEquals(HttpStatusCode.Created, response.first.response)
    }





    //endregion


}