package Infraestructure

import Model.User
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class MemoryRepositoryShould {

    @Test
    fun `Be able to add users to the list`(){
        val (memoryRepository, user) = GivenAUser()

        WhenRegisterThisUser(memoryRepository, user)

        ThenTheMapHasOneRegister(memoryRepository)
    }

    @Test
    fun `Get users by nickname`(){
        val (memoryRepository, user) = GivenAUser()

        WhenRegisterThisUser(memoryRepository, user)

        ThenGetUserByNickname(user, memoryRepository)
    }

    @Test
    fun `Check if a user exists only knowing nickname`(){

        val (memoryRepository, user) = GivenAUser()

        WhenRegisterThisUser(memoryRepository, user)

        ThenCheckIfExists(memoryRepository)
    }

    @Test
    fun `Be able to edit users name and lastname`(){

        val (memoryRepository, user) = GivenAUser()

        WhenTryToEditTheNameAndLastName(memoryRepository, user)

        ThenTheNameAndLastnameChanged(user)
    }

    @Test
    fun `Allow user to follow another one with nickname`(){
        val (memoryRepository, user) = GivenAUser()
         User("Pedro", "Lopez", "Pepe")



        assertTrue { memoryRepository.FollowUser(user.nickName, "Pepe") }
    }


    //region Private Methods

    private fun GivenAUser(): Pair<MemoryRepository, User> {
        val memoryRepository = MemoryRepository()
        val newUser = User("Rodrigo", "Fernandez", "Rodri")
        return Pair(memoryRepository, newUser)
    }


    private fun WhenRegisterThisUser(memoryRepository: MemoryRepository, user: User) {
        memoryRepository.AddUser(user)
    }


    private fun WhenTryToEditTheNameAndLastName(memoryRepository: MemoryRepository, user: User) {
        memoryRepository.AddUser(user)
        memoryRepository.EditUser(user, "Alberto", "Perez")
    }


    private fun ThenGetUserByNickname(user: User, memoryRepository: MemoryRepository) {
        assertEquals(user, memoryRepository.GetUser(user.nickName))
    }


    private fun ThenCheckIfExists(memoryRepository: MemoryRepository) {
        assertTrue { memoryRepository.ExistUser("Rodri") }
    }


    private fun ThenTheMapHasOneRegister(memoryRepository: MemoryRepository) {
        assertEquals(1, memoryRepository.userMap.count())
    }

    private fun ThenTheNameAndLastnameChanged(user: User) {
        assertEquals("Alberto", user.name)
        assertEquals("Perez", user.lastName)
    }



    //endregion

}