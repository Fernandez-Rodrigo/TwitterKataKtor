package Actions

import Action.EditUserData
import DataClasses.UserData
import Interfaces.IUserService
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class EditUserDataShould {

    @Test
    fun `Execute Change name fun from UserService`(){
        val (mockUserService: IUserService, editUserData) = GivenAnInstanceOfEditUserData()

        val newUserdata = WhenExecuteChangeNameFunction(editUserData)

        ThenCallTheChangeNameFunctionOnce(mockUserService, newUserdata)

    }

    //region Private Methods

    private fun ThenCallTheChangeNameFunctionOnce(
        mockUserService: IUserService,
        newUserdata: UserData
    ) {
        verify(mockUserService).ChangeName(newUserdata)
    }

    private fun WhenExecuteChangeNameFunction(editUserData: EditUserData): UserData {
        val newUserdata = UserData("Alberto", "Perez", "Rodri")
        editUserData.ExecuteChangeName(newUserdata)
        return newUserdata
    }

    private fun GivenAnInstanceOfEditUserData(): Pair<IUserService, EditUserData> {
        val mockUserService: IUserService = mock()
        val editUserData = EditUserData(mockUserService)
        return Pair(mockUserService, editUserData)
    }

    //endregion
}