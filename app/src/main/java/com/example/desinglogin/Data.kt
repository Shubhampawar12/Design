import java.io.Serializable

data class Data(var postId: Int,
                var id: Int,
                var name: String,
                var email: String,
                var body: String): Serializable