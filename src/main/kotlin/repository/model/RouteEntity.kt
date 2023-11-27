package repository.model

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class RouteEntity(id: EntityID<Long>): LongEntity(id) {
    companion object: LongEntityClass<RouteEntity>(RoutesTable)

    val origin by AirportEntity referencedOn RoutesTable.origin
    val destination by AirportEntity referencedOn RoutesTable.destination
    val durationMinutes by RoutesTable.durationMinutes
}