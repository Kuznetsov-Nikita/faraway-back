package repository.model

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Column

object TicketsTable: LongIdTable("tickets") {
    var owner: Column<EntityID<Long>?> = reference("owner", UsersTable).nullable()
    val flight: Column<EntityID<Long>> = reference("flight", FlightsTable)
    val seatClass: Column<String> = varchar("class", length = 100)
    val price: Column<Double> = double("price")
    val luggageKg: Column<Double> = double("luggage_kg")
    val handLuggageKg: Column<Double> = double("hand_luggage_kg")
    val refundable: Column<Boolean> = bool("refundable")
}