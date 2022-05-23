package Action

import Interfaces.IUserService

class FollowUsers(private val userServicesInterface: IUserService) {

    fun ExecuteFollow(user : String, followedUser : String) : String{

         return userServicesInterface.FollowUser(user, followedUser)
    }

}