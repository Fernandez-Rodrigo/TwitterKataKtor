import Action.FollowUsers
import Interfaces.IUserService
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class FollowUsersShould {

    @Test
    fun `Execute Follow function`(){

        val mockUserService : IUserService = mock()
        val followUsers = FollowUsers(mockUserService)

        followUsers.ExecuteFollow("ToTy","Rodri")

        verify(mockUserService).FollowUser("ToTy","Rodri")

    }

}