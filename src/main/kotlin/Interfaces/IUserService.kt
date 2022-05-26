package Interfaces

import DataClasses.UserData

interface IUserService {

    fun Register(userData: UserData) : String

    fun ChangeName(newUserData : UserData) : String

    fun FollowUser(user : String, followedUser : String) : String

    fun GetFollowersList(nickName : String) :  MutableList<String>

    fun GetUserInfo(nickName : String) : UserData

}