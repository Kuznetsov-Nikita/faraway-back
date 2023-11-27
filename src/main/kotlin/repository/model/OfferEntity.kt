package repository.model

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class OfferEntity(id: EntityID<Long>): LongEntity(id) {
    companion object: LongEntityClass<OfferEntity>(OffersTable)

    val city by OffersTable.city
    val photoUrl by OffersTable.photoUrl
    val departureDate by OffersTable.departureDate
    val arrivalDate by OffersTable.arrivalDate
}