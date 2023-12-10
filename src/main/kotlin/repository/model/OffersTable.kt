package repository.model

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Column

object OffersTable: LongIdTable("offers") {
    val city: Column<String> = varchar("city", length = 100)
    val photoUrl: Column<String> = varchar("photo_url", length = 500)
    val departureDate: Column<String> = varchar("departure_date", length = 10)
    val arrivalDate: Column<String> = varchar("arrival_date", length = 10)
    val price: Column<Double> = double("price")
}