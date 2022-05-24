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
        return if(userRepoInterface.GetUser(user) != null && userRepoInterface.GetUser(followedUser) != null){
            if(userRepoInterface.FollowUser(user, followedUser))
            {
                return "Ya sigues a este usuario"
            }else
            {return "El usuario $followedUser se ha agregado a tu lista de seguidos"}

        }else{
            "El usuario no existe"
        }


    }

    override fun GetFollowersList(nickName: String) : MutableList<String> {

        return userRepoInterface.GetFollowersList(nickName)
    }

    private fun CheckNicknameDuplication(nickName: String): Boolean {

        return userRepoInterface.ExistUser(nickName)
    }

}