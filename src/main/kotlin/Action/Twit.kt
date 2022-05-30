package Action

import Interfaces.ITwitService
import Interfaces.IUserService
import Services.UserService

class Twit(val userService : ITwitService) {

    fun ExecuteTwit(message : String){

        userService.Twit(message)

    }

}