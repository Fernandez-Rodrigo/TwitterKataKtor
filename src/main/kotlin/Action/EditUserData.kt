package Action

import DataClasses.UserData
import Interfaces.IUserService


class EditUserData(private val userServicesInterface: IUserService) {

    fun ExecuteChangeName(newUserData : UserData) : String{

            return userServicesInterface.ChangeName(newUserData)
    }

}