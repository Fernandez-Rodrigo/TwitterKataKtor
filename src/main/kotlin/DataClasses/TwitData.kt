package DataClasses
import kotlinx.serialization.Serializable

@Serializable
data class TwitData(val nickName : String, val message : String)
