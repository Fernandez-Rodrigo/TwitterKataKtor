import DataClasses.UserData
import Enum.ResponseEnum
import Interfaces.IUserService

class RegisterUser (private val iUserService: IUserService){

        fun ExecuteRegister(userData : UserData) : ResponseEnum
        {
            return iUserService.Register(userData)
        }

}