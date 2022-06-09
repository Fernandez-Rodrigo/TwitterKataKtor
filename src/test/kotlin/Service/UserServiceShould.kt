package Service

import DataClasses.UserData
import Enum.ResponseEnum
import Interfaces.IUserRepository
import Model.User
import Services.UserService
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

class UserServiceShould {
    @Test
    fun `Be able to add a new user to user list`(){

        val (mockUserRepository: Interfaces.IUserRepository, userService) = GivenAnInstanceOfUserService()

        val userData = WhenANewUserIsRegistered(userService)

        ThenExecuteTheAddFunctionWithUserData(mockUserRepository, userData)
    }

    @Test
    fun `Check if the nickname already exists`(){

        val (mockUserRepository: Interfaces.IUserRepository, userService) = GivenAnInstanceOfUserService()

        val userData = WhenANewUserIsRegistered(userService)

        ThenCheckIfUserExists(mockUserRepository, userData)
    }

    @Test
    fun `Be able to change the real name of the user`(){

        val (mockUserRepository: Interfaces.IUserRepository, userService) = GivenAnInstanceOfUserService()
        whenever(mockUserRepository.GetUser("Rodri")).thenReturn(User("Rodrigo", "Fernandez", "Rodri"))

        val (user, newUserData) = WhenRegisterAndTryToChangeTheUserData(userService)

        ThenCheckIfEditUserFunIsCalled(mockUserRepository, user, newUserData)
    }

    @Test
    fun `Allow users to follow another user`(){
        val (mockUserRepository: Interfaces.IUserRepository, userService) = GivenTwoUsersAndUserServiceInstance()
        whenever(mockUserRepository.GetUser("Pedro")).thenReturn(User("Rodrigo", "Fernandez", "Pedro"))
        whenever(mockUserRepository.GetUser("Lele")).thenReturn(User("Nicolas", "Soria", "Lele"))
        whenever(mockUserRepository.FollowUser("Pedro", "Lele")).thenReturn(true)

        val receivedMessage = WhenFollowAnotherUser(userService)

        ThenExpectTheSuccesMessage(receivedMessage)

    }


    //region Private Methods
    private fun GivenAnInstanceOfUserService(): Pair<IUserRepository, UserService> {
        val mockUserRepository: IUserRepository = mock()
        val userService = UserService(mockUserRepository)
        return Pair(mockUserRepository, userService)
    }
    private fun GivenTwoUsersAndUserServiceInstance(): Pair<IUserRepository, UserService> {
        val mockUserRepository: IUserRepository = mock()
        val userService = UserService(mockUserRepository)
        userService.Register(UserData("Rodrigo", "Fernandez", "Rodri"))
        userService.Register(UserData("Nicolas", "Soria", "Nico"))
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

    private fun WhenFollowAnotherUser(userService: UserService): ResponseEnum {
        return userService.FollowUser("Pedro", "Lele")
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
    ) {
        verify(mockUserRepository).EditUser(user, newUserData.name, newUserData.lastName)
    }
    private fun ThenExpectTheSuccesMessage(receivedMessage: ResponseEnum) {
        val expectedMessage = ResponseEnum.FOUND
        assertEquals(expectedMessage, receivedMessage)
    }


//endregion


}