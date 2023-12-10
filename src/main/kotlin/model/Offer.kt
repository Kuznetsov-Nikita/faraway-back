package model

import kotlinx.serialization.Serializable

@Serializable
data class Offer(
    val id: Long,
    val city: String,
    val photoUrl: String,
    val departureDate: String,
    val arrivalDate: String,
    val price: Double
)
