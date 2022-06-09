package Action

import DataClasses.TwitData
import Enum.ResponseEnum
import Interfaces.ITwitService

class Twit(private val twitService : ITwitService) {

    fun ExecuteTwit(twitData : TwitData) : ResponseEnum{

       return twitService.Twit(twitData.nickName, twitData.message)

    }

}