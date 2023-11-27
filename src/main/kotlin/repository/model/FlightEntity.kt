package repository.model

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class FlightEntity(id: EntityID<Long>): LongEntity(id) {
    companion object: LongEntityClass<FlightEntity>(FlightsTable)

    val airline by FlightsTable.airline
    val route by RouteEntity referencedOn FlightsTable.route
    val aircraft by AircraftEntity referencedOn FlightsTable.aircraft
    val depTime by FlightsTable.depTime
}