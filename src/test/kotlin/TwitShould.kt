import Action.Twit
import Interfaces.ITwitService
import Services.TwitService
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class TwitShould {

    @Test
    fun `Execute Twit action`(){

        val (mockTwitService: ITwitService, twit) = `Given Twit instance`()

        `When try to twit`(twit)

        `Then expect to execute twit action`(mockTwitService)

    }



//region Private Methods
    private fun `Given Twit instance`(): Pair<ITwitService, Twit> {
        val mockTwitService: ITwitService = mock()
        val twit = Twit(mockTwitService)
        return Pair(mockTwitService, twit)
    }

    private fun `When try to twit`(twit: Twit) {
        twit.ExecuteTwit("Twit")
    }


    private fun `Then expect to execute twit action`(mockTwitService: ITwitService) {
        verify(mockTwitService).Twit("Twit")
    }


//endregion


}