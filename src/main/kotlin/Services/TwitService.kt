package Services

import Interfaces.ITwitService
import Interfaces.IUserRepository

class TwitService (val userRepoInterface : IUserRepository): ITwitService {

    override fun Twit(message: String) {

        userRepoInterface.Twit(message)

    }
}