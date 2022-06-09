package Service

import Enum.ResponseEnum
import Interfaces.ITwitRepository
import Interfaces.IUserRepository
import Model.User
import Services.TwitService
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

class TwitServiceShould {

    @Test
    fun `Call twit function on repository with correct data`(){

        val mockTwitRepo : ITwitRepository = mock()
        val twitService = TwitService(mockTwitRepo)


        twitService.Twit("Rodri", "Mensaje de prueba")

        verify(mockTwitRepo).Twit("Rodri", "Mensaje de prueba")
    }

    @Test
    fun `Return correct response if data is OK on Twits`(){
        val mockTwitRepo : ITwitRepository = mock()
        val twitService = TwitService(mockTwitRepo)

        val response = twitService.Twit("Rodri", "Mensaje de prueba")

        assertEquals(ResponseEnum.SUCCESS, response)
    }


    @Test
    fun `Call GetTwits function on repository with correct data`(){

        val mockTwitRepo : ITwitRepository = mock()
        val twitService = TwitService(mockTwitRepo)


        twitService.GetTwits("Rodri")

        verify(mockTwitRepo).GetTwits("Rodri")
    }

    @Test
    fun `Return correct response if data is OK on GetTwits`(){

        val mockTwitRepo : ITwitRepository = mock()
        val twitService = TwitService(mockTwitRepo)

        val response = twitService.GetTwits("Rodri")

        assertEquals(ResponseEnum.FOUND, response.first)
    }

}