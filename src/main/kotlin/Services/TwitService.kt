package Services

import Enum.ResponseEnum
import Interfaces.ITwitRepository
import Interfaces.ITwitService
import Interfaces.IUserRepository

class TwitService (private val twitRepoInterface : ITwitRepository): ITwitService {

    override fun Twit(nickName: String, message: String): ResponseEnum {

            twitRepoInterface.Twit(nickName, message)
            return ResponseEnum.SUCCESS
    }

    override fun GetTwits(nickName: String): Pair<ResponseEnum, List<String>> {

          return Pair(ResponseEnum.SUCCESS, twitRepoInterface.GetTwits(nickName))
    }
}