package repository.model

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class TicketEntity(id: EntityID<Long>): LongEntity(id) {
    companion object: LongEntityClass<TicketEntity>(TicketsTable)

    var owner by UserEntity optionalReferencedOn TicketsTable.owner
    val flight by FlightEntity referencedOn TicketsTable.flight
    val seatClass by TicketsTable.seatClass
    val price by TicketsTable.price
    val luggageKg by TicketsTable.luggageKg
    val handLuggageKg by TicketsTable.handLuggageKg
    val refundable by TicketsTable.refundable
}