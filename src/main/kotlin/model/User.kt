package model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Long,
    var firstName: String,
    var lastName: String,
    var phone: String?,
    var email: String,
    var password: String,
    var city: String,
    var country: String,
)
