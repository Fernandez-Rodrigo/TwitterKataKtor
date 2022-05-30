package Action

import DataClasses.UserData
import Enum.ResponseEnum
import Interfaces.IUserService

class GetUserData(private val userServicesInterface: IUserService){

    fun ExecuteGetUserData(nickName : String) : Pair<ResponseEnum, UserData>{

       return userServicesInterface.GetUserInfo(nickName)
    }

}