package Action

import Enum.ResponseEnum
import Interfaces.IUserService

class FollowUsers(private val userServicesInterface: IUserService) {

    fun ExecuteFollow(user : String, followedUser : String) : ResponseEnum {

         return userServicesInterface.FollowUser(user, followedUser)
    }

}