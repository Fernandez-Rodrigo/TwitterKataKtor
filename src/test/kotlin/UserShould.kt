import DataClasses.UserData
import Model.User
import org.junit.Test
import kotlin.test.assertEquals

class UserShould {



    @Test
    fun `Be Created With Real Name`(){

        val user = GivenANewUser()

        val (nameReceived, lastNameReceived) = WhenRegisterWithRealName(userData = UserData("Rodrigo", "Fernandez", "Rodrigo") )

        ThenCheckTheNameAndLastName(nameReceived, lastNameReceived)
    }


    //region Private Methods
    private fun GivenANewUser(): User {
        val userData = UserData("Rodrigo", "Fernandez", "Rodrigo")
        return User(userData.name, userData.lastName, userData.nickName)
    }

    private fun WhenRegisterWithRealName(userData: UserData): Pair<String, String> {
        val nameReceived = userData.name
        val lastNameReceived = userData.lastName
        return Pair(nameReceived, lastNameReceived)
    }


    private fun ThenCheckTheNameAndLastName(nameReceived: String, lastNameReceived: String) {
        assertEquals("Rodrigo Fernandez", "$nameReceived $lastNameReceived")
    }


    //endregion


}