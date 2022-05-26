import DataClasses.UserData
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.testing.*
import org.junit.Test
import kotlin.test.assertEquals

class UserRoutesShould {

    @Test
    fun `Get user info by nickname`() = testApplication {
        val response = client.get("/GetUser/Rodri")

       assertEquals("Nombre: Rodrigo Apellido: Fernandez Nickname: Rodri",
           response.bodyAsText()
        )
        assertEquals(HttpStatusCode.OK, response.status)
    }

    @Test
    fun `Call register user action and return correct message`() = testApplication{
        
      val client = createClient {
         this@testApplication.install(ContentNegotiation) {
                json()
           }
        }
        val response = client.post(){
            contentType(ContentType.Application.Json)
            setBody(UserData("Rodrigo", "Fernandez", "Rodri"))
        }
        assertEquals(HttpStatusCode.Created, response.status)
    }



    //region Private Methods

    //endregion

}