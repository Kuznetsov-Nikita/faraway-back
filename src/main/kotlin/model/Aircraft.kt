package model

import kotlinx.serialization.Serializable

@Serializable
data class Aircraft(
    val id: Long,
    val manufacturer: String,
    val model: String,
    val seats: ULong
)
