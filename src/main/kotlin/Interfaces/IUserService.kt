package Interfaces

import DataClasses.UserData
import Enum.ResponseEnum

interface IUserService {

    fun Register(userData: UserData) : ResponseEnum

    fun ChangeName(newUserData : UserData) : ResponseEnum

    fun FollowUser(user : String, followedUser : String) : ResponseEnum

    fun GetFollowersList(nickName : String) :  Pair<ResponseEnum, MutableList<String>>

    fun GetUserInfo(nickName : String) : Pair<ResponseEnum, UserData>


}