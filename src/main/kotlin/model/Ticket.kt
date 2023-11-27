package model

import kotlinx.serialization.Serializable

@Serializable
data class Ticket(
    val id: Long,
    var owner: Long?,
    val flight: Flight,
    val seatClass: String,
    val price: Double,
    val luggageKg: Double,
    val handLuggageKg: Double,
    val refundable: Boolean,
)
