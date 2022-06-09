package Action

import Enum.ResponseEnum
import Interfaces.ITwitService

class GetTwits(private val twitServiceInterface : ITwitService) {

    fun ExecuteGetTwits(nickName : String) : Pair<ResponseEnum, List<String>>{

        return twitServiceInterface.GetTwits(nickName)

    }

}