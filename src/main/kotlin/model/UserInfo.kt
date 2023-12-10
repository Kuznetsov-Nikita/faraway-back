package model

import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phone: String?,
    val city: String,
    val country: String,
)
