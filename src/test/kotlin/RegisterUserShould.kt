import DataClasses.UserData
import Interfaces.IUserService
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify


class RegisterUserShould {

    @Test
    fun `Call Register function from UserService`(){

        val (mockUserService: IUserService, registerUser) = GivenAnInstanceOfRegisterUser()

        WhenRegisterAUser(registerUser)

        ThenCallTheRegisterMethodOfUserService(mockUserService)

    }


    //region Private Methods
    private fun ThenCallTheRegisterMethodOfUserService(mockUserService: IUserService) {
        verify(mockUserService).Register(userData = UserData("Rodrigo", "Fernandez", "Rodri" ))
    }

    private fun WhenRegisterAUser(registerUser: RegisterUser) {
        val userData = UserData("Rodrigo", "Fernandez", "Rodri")
        registerUser.ExecuteRegister(userData)
    }

    private fun GivenAnInstanceOfRegisterUser(): Pair<IUserService, RegisterUser> {
        val mockUserService: IUserService = mock()
        val registerUser = RegisterUser(mockUserService)
        return Pair(mockUserService, registerUser)
    }
    //endregion
}