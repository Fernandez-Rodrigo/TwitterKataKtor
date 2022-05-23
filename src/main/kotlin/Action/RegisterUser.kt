import DataClasses.UserData
import Interfaces.IUserService

class RegisterUser (private val iUserService: IUserService){

        fun ExecuteRegister(userData : UserData) : String
        {
            return iUserService.Register(userData)
        }

}