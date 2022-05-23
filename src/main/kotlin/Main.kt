import Action.CheckFollowers
import Action.EditUserData
import Action.FollowUsers
import DataClasses.UserData
import Infraestructure.MemoryRepository
import Services.UserService

fun main(args: Array<String>) {

    val memoryRepository = MemoryRepository()
    val userService = UserService(memoryRepository)
    val registerUser = RegisterUser(userService)
    val editUserData = EditUserData(userService)
    val followUser = FollowUsers(userService)
    val checkFollowers = CheckFollowers(userService)
    val user1Data = UserData("Rodrigo",  "Fernandez", "Rodrigo")
    var user2Data = UserData("Rodrigo", "Fernandez", "ToTy")
    val user3Data = UserData("Rodrigo", "Fernandez", "Rodri")
    val user4Data = UserData("Rodrigo", "Fernandez", "Rodrigo")
    val user2newData = UserData( "Alberto", "Perez", "ToTy")



    println(registerUser.ExecuteRegister(user1Data))

    println(registerUser.ExecuteRegister(user2Data))

    println(registerUser.ExecuteRegister(user3Data))

    println(registerUser.ExecuteRegister(user4Data))

    println(editUserData.ExecuteChangeName(user2newData))

    println(followUser.ExecuteFollow(user1Data.nickName, user2Data.nickName))

    checkFollowers.ExecuteCheck(user1Data.nickName)?.forEach{ follower -> println(follower.nickName)}

}