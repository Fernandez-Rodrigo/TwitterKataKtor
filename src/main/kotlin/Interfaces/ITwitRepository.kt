package Interfaces

interface ITwitRepository {

    fun Twit(nickName: String, message : String)

    fun GetTwits(nickName : String) : List<String>

}