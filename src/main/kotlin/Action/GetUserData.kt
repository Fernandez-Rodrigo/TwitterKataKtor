package Action

import DataClasses.UserData
import Interfaces.IUserService

class GetUserData(private val userServicesInterface: IUserService){

    fun ExecuteGetUserData(nickName : String) : UserData{

       return userServicesInterface.GetUserInfo(nickName)
    }

}