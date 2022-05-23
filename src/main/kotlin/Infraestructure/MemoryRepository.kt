package Infraestructure

import Interfaces.IUserRepository
import Model.User

class MemoryRepository : IUserRepository{

    var userMap = mutableMapOf<String, User>()

    override fun AddUser(newUser: User) {
        userMap[newUser.nickName] = newUser
    }

    override fun GetUser(nickName: String): User? {

        return userMap[nickName]
    }

    override fun EditUser(user : User, newName : String, newLastName : String){
        user.name = newName
        user.lastName = newLastName

        userMap[user.nickName] = user
    }

    override fun ExistUser(nickName : String) : Boolean {

        return userMap.containsKey(nickName)

    }

}