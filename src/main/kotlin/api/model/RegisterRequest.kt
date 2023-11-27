package api.model

data class RegisterRequest(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val city: String,
    val country: String,
    val phone: String?,
)
