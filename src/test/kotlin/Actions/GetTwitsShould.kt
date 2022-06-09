package Actions

import Action.GetTwits
import Interfaces.ITwitService
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class GetTwitsShould {

    @Test
    fun `Call the correct function on the service with the given parameters`(){

        val mockTwitService : ITwitService = mock()
        val getTwits = GetTwits(mockTwitService)

        getTwits.ExecuteGetTwits("Rodri")

        verify(mockTwitService).GetTwits("Rodri")

    }
}