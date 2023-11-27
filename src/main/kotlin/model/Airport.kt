package model

import kotlinx.serialization.Serializable

@Serializable
data class Airport(
    val id: Long,
    val name: String,
    val city: String,
    val country: String,
)