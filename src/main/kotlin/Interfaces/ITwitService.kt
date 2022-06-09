package Interfaces

import Action.GetTwits
import Enum.ResponseEnum

interface ITwitService {

    fun Twit(nickName : String, message : String) : ResponseEnum

    fun GetTwits(nickName : String) : Pair<ResponseEnum, List<String>>
}