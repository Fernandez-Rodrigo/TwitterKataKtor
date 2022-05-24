import Infraestructure.RedisRepository
import Model.User
import com.github.fppt.jedismock.RedisServer
import org.junit.Test
import redis.clients.jedis.JedisPooled
import kotlin.test.assertEquals
import kotlin.test.assertTrue


class RedisRepositoryShould {

    var server = RedisServer
        .newRedisServer()
        .start()

    @Test
    fun `Be able to register a user`() {

        val (redisRepo, newUser) = GivenARedisRepositoryInstanceAndOneUser()

        WhenAddTheUser(redisRepo, newUser)

        ThenTheUserIsInTheDataBase(redisRepo)
    }

    @Test
    fun `Be able to return a user with nickname`(){
        val (redisRepo, expectedUser) = GivenARedisRepositoryInstanceAndOneUser()

        WhenAddTheUser(redisRepo, expectedUser)

        ThenCanGetTheSameUser(redisRepo, expectedUser)
    }


    @Test
    fun `Be able to check if user is in database`(){
        val (redisRepo, expectedUser) = GivenARedisRepositoryInstanceAndOneUser()

        WhenAddTheUser(redisRepo, expectedUser)

        ThenCheckIfTheUserExists(redisRepo)
    }

    @Test
    fun `Be able to edit user name and last name`(){
        val (redisRepo, expectedUser) = GivenARedisRepositoryInstanceAndOneUser()

        WhenTryToEditTheNameAndLastname(redisRepo, expectedUser)

        ThenExpectToGetTheNewDataFromWithTheSameNickname(redisRepo)
    }

    @Test
    fun `Allows to follow a user`(){

        val (redisRepo, newUser, userToFollow) = GivenARedisRepoAndTwoUsers()

        val expectTrue = `When one user try to follow another`(redisRepo, newUser, userToFollow)


        `Then expect true`(expectTrue)
    }

    @Test
    fun `Return a list of the followers`(){
        val (redisRepo, expectedUser) = GivenARedisRepositoryInstanceAndOneUser()

        GivenFourDifferentUsers(redisRepo)

        `When expectedUser try to follow all of them`(redisRepo, expectedUser)

        `Then expect the follower list to have four entries`(redisRepo, expectedUser)

    }





    //region Private Methods

    private fun GivenARedisRepositoryInstanceAndOneUser(): Pair<RedisRepository, User> {
        val pool = JedisPooled(server.host, server.bindPort)
        val redisRepo = RedisRepository(pool)
        val newUser = User("Rodrigo", "Fernandez", "Rodri")
        return Pair(redisRepo, newUser)
    }
    private fun GivenARedisRepoAndTwoUsers(): Triple<RedisRepository, User, User> {
        val pool = JedisPooled(server.host, server.bindPort)
        val redisRepo = RedisRepository(pool)
        val newUser = User("Rodrigo", "Fernandez", "Rodri")
        val userToFollow = User("Pedro", "Perez", "Pepe")
        return Triple(redisRepo, newUser, userToFollow)
    }

    private fun GivenFourDifferentUsers(redisRepo: RedisRepository) {
        redisRepo.AddUser(User("UserName", "UserLastname", "User1"))
        redisRepo.AddUser(User("UserName", "UserLastname", "User2"))
        redisRepo.AddUser(User("UserName", "UserLastname", "User3"))
        redisRepo.AddUser(User("UserName", "UserLastname", "User4"))
    }

    private fun WhenAddTheUser(redisRepo: RedisRepository, newUser: User) {
        redisRepo.AddUser(newUser)
    }

    private fun WhenTryToEditTheNameAndLastname(redisRepo: RedisRepository, expectedUser: User) {
        redisRepo.EditUser(expectedUser, "Alberto", "Perez")
    }

    private fun `When one user try to follow another`(
        redisRepo: RedisRepository,
        newUser: User,
        userToFollow: User
    ): Boolean {
        redisRepo.AddUser(newUser)
        redisRepo.AddUser(userToFollow)
        val expectTrue = redisRepo.FollowUser(newUser.nickName, userToFollow.nickName)
        return expectTrue
    }

    private fun `When expectedUser try to follow all of them`(
        redisRepo: RedisRepository,
        expectedUser: User
    ) {
        redisRepo.FollowUser(expectedUser.nickName, "User1")
        redisRepo.FollowUser(expectedUser.nickName, "User2")
        redisRepo.FollowUser(expectedUser.nickName, "User3")
        redisRepo.FollowUser(expectedUser.nickName, "User4")
    }

    private fun ThenTheUserIsInTheDataBase(redisRepo: RedisRepository) {
        assertTrue { redisRepo.server.exists("Rodri") }
    }

    private fun ThenCanGetTheSameUser(redisRepo: RedisRepository, expectedUser: User) {
        val user = redisRepo.GetUser("Rodri")
        assertEquals(expectedUser, user)
    }

    private fun ThenCheckIfTheUserExists(redisRepo: RedisRepository) {
        val exist = redisRepo.ExistUser("Rodri")
        assertTrue { exist }
    }

    private fun `Then expect true`(expectTrue: Boolean) {
        assertTrue { expectTrue }
    }

    private fun ThenExpectToGetTheNewDataFromWithTheSameNickname(redisRepo: RedisRepository) {
        assertEquals(User("Alberto", "Perez", "Rodri"), redisRepo.GetUser("Rodri"))
    }

    private fun `Then expect the follower list to have four entries`(
        redisRepo: RedisRepository,
        expectedUser: User
    ) {
        assertEquals(4, redisRepo.GetFollowersList(expectedUser.nickName).count())
    }

    //endregion

}