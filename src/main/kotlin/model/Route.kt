package model

import kotlinx.serialization.Serializable

@Serializable
data class Route(
    val id: Long,
    val origin: Airport,
    val destination: Airport,
    val durationMinutes: ULong,
)
