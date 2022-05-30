package DataClasses
import kotlinx.serialization.Serializable
@Serializable
data class FollowData(val userNickname : String, val userToFollowNickname : String)
