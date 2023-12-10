package api.model

import kotlinx.serialization.Serializable

@Serializable
data class CustomerRequest(
    val id: Long,
    val firstName: String? = null,
    val lastName: String? = null,
    val email: String? = null,
    val phone: String? = null,
    val city: String? = null,
    val country: String? = null,
)
