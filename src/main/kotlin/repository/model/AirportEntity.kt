package repository.model

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class AirportEntity(id: EntityID<Long>): LongEntity(id) {
    companion object: LongEntityClass<AirportEntity>(AirportsTable)

    val name by AirportsTable.name
    val city by AirportsTable.city
    val country by AirportsTable.country
}