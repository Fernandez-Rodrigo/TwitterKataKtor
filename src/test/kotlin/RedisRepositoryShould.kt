import Infraestructure.RedisRepository
import Model.User
import com.github.fppt.jedismock.RedisServer
import org.junit.Test
import redis.clients.jedis.JedisPool
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





    //region Private Methods

    private fun GivenARedisRepositoryInstanceAndOneUser(): Pair<RedisRepository, User> {
        val pool = JedisPooled(server.host, server.bindPort)
        val redisRepo = RedisRepository(pool)
        val newUser = User("Rodrigo", "Fernandez", "Rodri")
        return Pair(redisRepo, newUser)
    }

    private fun WhenAddTheUser(redisRepo: RedisRepository, newUser: User) {
        redisRepo.AddUser(newUser)
    }

    private fun ThenTheUserIsInTheDataBase(redisRepo: RedisRepository) {
        assertTrue { redisRepo.server.exists("Rodri") }
    }

    private fun ThenCanGetTheSameUser(redisRepo: RedisRepository, expectedUser: User) {
        val user = redisRepo.GetUser("Rodri")
        assertEquals(expectedUser, user)
    }

    private fun ThenCheckIfTheUserExists(redisRepo: RedisRepository) {
        val exist = redisRepo.CheckUserExist("Rodri")
        assertTrue { exist }
    }

    //endregion

}