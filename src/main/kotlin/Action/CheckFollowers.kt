package Action

import Interfaces.IUserService
import Model.User

class CheckFollowers(private val userServicesInterface: IUserService) {

    fun ExecuteCheck(nickName: String) : MutableList<User>? {

       return userServicesInterface.GetFollowersList(nickName)

    }

}