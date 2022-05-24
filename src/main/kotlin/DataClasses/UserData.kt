package DataClasses
import kotlinx.serialization.Serializable
@Serializable
data class UserData (var name : String, var lastName : String, val nickName : String){


}