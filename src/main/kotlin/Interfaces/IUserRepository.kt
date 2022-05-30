package Interfaces

import Action.EditUserData
import DataClasses.UserData
import Model.User

interface IUserRepository {
    fun AddUser(newUser:User)

    fun GetUser(nickName : String) : User?

    fun EditUser(user : User, newName : String, newLastName : String)

    fun ExistUser(nickName : String) : Boolean

    fun FollowUser(userNickname : String, userToFollowNickname : String) : Boolean
    fun GetFollowersList(userNickname : String) : MutableList<String>

    fun Twit(message : String)

}