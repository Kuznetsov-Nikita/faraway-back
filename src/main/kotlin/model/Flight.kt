package model

import kotlinx.serialization.Serializable

@Serializable
data class Flight(
    val id: Long,
    val airline: String,
    val route: Route,
    val aircraft: Aircraft,
    val depTime: String,
)
