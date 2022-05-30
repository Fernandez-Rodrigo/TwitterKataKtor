package Action

import DataClasses.UserData
import Enum.ResponseEnum
import Interfaces.IUserService


class EditUserData(private val userServicesInterface: IUserService) {

    fun ExecuteChangeName(newUserData : UserData) : ResponseEnum {

            return userServicesInterface.ChangeName(newUserData)
    }

}