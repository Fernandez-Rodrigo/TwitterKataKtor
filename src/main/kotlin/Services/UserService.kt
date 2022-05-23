package Services

import DataClasses.UserData
import Interfaces.IUserRepository
import Interfaces.IUserService
import Model.User

class UserService (private val userRepoInterface : IUserRepository) : IUserService{

    override fun Register(userData : UserData) : String
    {
        return if(CheckNicknameDuplication(userData.nickName)){
            "Error, Nickname ya utilizado"
        } else {  userRepoInterface.AddUser(User(userData.name, userData.lastName, userData.nickName))
            "Usuario ${userData.nickName} registrado correctamente."
        }
    }

    override fun ChangeName(newUserData: UserData) : String{
       val user = userRepoInterface.GetUser(newUserData.nickName)
        return if (user != null) {
           userRepoInterface.EditUser(user, newUserData.name, newUserData.lastName)
            "El nombre y apellido del usuario fueron actualizados correctamente"
        } else{
            "Error, no se pudo actualizar el nombre y apellido del usuario"
        }
    }

    override fun FollowUser(user : String, followedUser : String): String {
            userRepoInterface.GetUser(followedUser)?.let {
            if(userRepoInterface.GetUser(user)?.followList?.contains(it) == true){
                return "Ya sigues a este usuario"
            }else
            { userRepoInterface.GetUser(user)?.followList?.add(it)
                return "El usuario $followedUser se ha agregado a tu lista de seguidos"}}


      return "No existe dicho usuario"

    }

    override fun GetFollowersList(nickName: String) : MutableList<User>? {

        return userRepoInterface.GetUser(nickName)?.followList
    }

    private fun CheckNicknameDuplication(nickName: String): Boolean {

        return userRepoInterface.ExistUser(nickName)
    }

}