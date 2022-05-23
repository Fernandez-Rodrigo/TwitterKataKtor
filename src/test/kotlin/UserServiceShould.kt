import DataClasses.UserData
import Interfaces.IUserRepository
import Model.User
import Services.UserService
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.*
import kotlin.test.assertEquals

class UserServiceShould {
    @Test
    fun `Be able to add a new user to user list`(){

        val (mockUserRepository: IUserRepository, userService) = GivenAnInstanceOfUserService()

        val userData = WhenANewUserIsRegistered(userService)

        ThenExecuteTheAddFunctionWithUserData(mockUserRepository, userData)
    }

    @Test
    fun `Check if the nickname already exists`(){

        val (mockUserRepository: IUserRepository, userService) = GivenAnInstanceOfUserService()

        val userData = WhenANewUserIsRegistered(userService)

        ThenCheckIfUserExists(mockUserRepository, userData)
    }

    @Test
    fun `Be able to change the real name of the user`(){

        val (mockUserRepository: IUserRepository, userService) = GivenAnInstanceOfUserService()
        whenever(mockUserRepository.GetUser("Rodri")).thenReturn(User("Rodrigo", "Fernandez", "Rodri"))

        val (user, newUserData) = WhenRegisterAndTryToChangeTheUserData(userService)

        ThenCheckIfEditUserFunIsCalled(mockUserRepository, user, newUserData)
    }

    @Test
    fun `Allow users to follow another user`(){

        val (mockUserRepository: IUserRepository, userService) = GivenAnInstanceOfUserService()
        whenever(mockUserRepository.GetUser("Rodri")).thenReturn(User("Rodrigo", "Fernandez", "Rodri"))
        whenever(mockUserRepository.GetUser("Pepe")).thenReturn(User("Pedro", "Flores", "Pepe"))

        val (anotherUser, actualMessage) = WhenTryToFollowAnotherUser(userService)

        ThenExpectSuccesFollowMessage(anotherUser, actualMessage)
    }

    @Test
    fun `Not allow to follow twice the same user`(){

        val (mockUserRepository: IUserRepository, userService) = GivenAnInstanceOfUserService()
        whenever(mockUserRepository.GetUser("Rodri")).thenReturn(User("Rodrigo", "Fernandez", "Rodri"))
        whenever(mockUserRepository.GetUser("Pepe")).thenReturn(User("Pedro", "Flores", "Pepe"))

        val actualMessage = WhenTryToFollowTheSameUserTwice(userService)

        ThenDontAddTheUser(actualMessage)

    }

}


    //region Private Methods
    private fun GivenAnInstanceOfUserService(): Pair<IUserRepository, UserService> {
        val mockUserRepository: IUserRepository = mock()
        val userService = UserService(mockUserRepository)
        return Pair(mockUserRepository, userService)
    }

    private fun WhenANewUserIsRegistered(userService: UserService): UserData {
        val userData = UserData("Rodrigo", "Fernandez", "Rodri")
        userService.Register(userData)
        return userData
    }

    private fun WhenRegisterAndTryToChangeTheUserData(userService: UserService): Pair<User, UserData> {
        val user = User("Rodrigo", "Fernandez", "Rodri")
        val newUserData = UserData("Alberto", "Perez", "Rodri")
        userService.ChangeName(newUserData)
        return Pair(user, newUserData)
    }

    private fun WhenTryToFollowAnotherUser(userService: UserService): Pair<UserData, String> {
       val anotherUser = UserData("Pedro", "Flores", "Pepe")
       val actualMessage = userService.FollowUser("Rodri", "Pepe")
       return Pair(anotherUser, actualMessage)
    }

    private fun WhenTryToFollowTheSameUserTwice(userService: UserService): String {
        userService.FollowUser("Rodri", "Pepe")
        return userService.FollowUser("Rodri", "Pepe")
    }


    private fun ThenExecuteTheAddFunctionWithUserData(
        mockUserRepository: IUserRepository,
        userData: UserData
    ) {
        Mockito.verify(mockUserRepository, times(1)).AddUser(User(userData.name, userData.lastName, userData.nickName))
    }
    private fun ThenCheckIfUserExists(mockUserRepository: IUserRepository, userData: UserData) {
        verify(mockUserRepository).ExistUser(userData.nickName)
    }
    private fun ThenCheckIfEditUserFunIsCalled(
       mockUserRepository: IUserRepository,
       user: User,
       newUserData: UserData
    ) {verify(mockUserRepository).EditUser(user, newUserData.name, newUserData.lastName)
      }
    private fun ThenExpectSuccesFollowMessage(anotherUser: UserData, actualMessage: String) {
      val expectedMessage = "El usuario ${anotherUser.nickName} se ha agregado a tu lista de seguidos"
      assertEquals(expectedMessage, actualMessage)
    }

    private fun ThenDontAddTheUser(actualMessage: String) {
        val expectedMessage = "Ya sigues a este usuario"
        assertEquals(expectedMessage, actualMessage)
    }


//endregion

