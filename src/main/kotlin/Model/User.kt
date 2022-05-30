package Model

data class User(var name: String, var lastName: String, val nickName: String) {

    var followList = mutableListOf<String>()

    var twitList = mutableListOf<String>()
}