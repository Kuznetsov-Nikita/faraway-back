package repository.model

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class AircraftEntity(id: EntityID<Long>): LongEntity(id) {
    companion object: LongEntityClass<AircraftEntity>(AirportsTable)

    val manufacturer by AircraftsTable.manufacturer
    val model by AircraftsTable.model
    val seats by AircraftsTable.seats
}