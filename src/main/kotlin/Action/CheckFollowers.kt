package Action

import Enum.ResponseEnum
import Interfaces.IUserService
import Model.User

class CheckFollowers(private val userServicesInterface: IUserService) {

    fun ExecuteCheck(nickName: String) : Pair<ResponseEnum, MutableList<String>> {

       return userServicesInterface.GetFollowersList(nickName)

    }

}