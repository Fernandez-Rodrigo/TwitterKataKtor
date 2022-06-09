package Services

import DataClasses.UserData
import Enum.ResponseEnum
import Interfaces.IUserRepository
import Interfaces.IUserService
import Model.User

class UserService (private val userRepoInterface : IUserRepository) : IUserService{

    override fun Register(userData : UserData) : ResponseEnum
    {
        return if(CheckNicknameDuplication(userData.nickName)){
            ResponseEnum.DUPLICATED
        } else { userRepoInterface.AddUser(User(userData.name, userData.lastName, userData.nickName))
            ResponseEnum.CREATED
        }
    }

    override fun ChangeName(newUserData: UserData) : ResponseEnum{
       val user = userRepoInterface.GetUser(newUserData.nickName)
        return if (user != null) {
           userRepoInterface.EditUser(user, newUserData.name, newUserData.lastName)
            ResponseEnum.SUCCESS
        } else{
            ResponseEnum.FAILURE
        }
    }

    override fun FollowUser(user : String, followedUser : String): ResponseEnum {
        return if(userRepoInterface.GetUser(user) != null && userRepoInterface.GetUser(followedUser) != null){
            if(userRepoInterface.FollowUser(user, followedUser))
            {
                return ResponseEnum.SUCCESS
            }else
            {return ResponseEnum.DUPLICATED}

        }else{
            ResponseEnum.FAILURE
        }
    }

    override fun GetFollowersList(nickName: String) : Pair<ResponseEnum, MutableList<String>> {

        return if (userRepoInterface.GetUser(nickName) != null) {
            Pair(ResponseEnum.SUCCESS, userRepoInterface.GetFollowersList(nickName))
        } else {
            Pair(ResponseEnum.FAILURE, mutableListOf())
        }
    }

    private fun CheckNicknameDuplication(nickName: String): Boolean {

        return userRepoInterface.ExistUser(nickName)
    }

    override fun GetUserInfo(nickName : String) : Pair<ResponseEnum, UserData> {
        val user = userRepoInterface.GetUser(nickName)
        return if(user != null){
            Pair(ResponseEnum.SUCCESS, UserData(user.name, user.lastName, user.nickName))
        }else {
            Pair(ResponseEnum.FAILURE, UserData("Null", "Null", "Null"))
        }

    }


}