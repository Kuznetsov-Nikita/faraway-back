package repository.model

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Column

object AircraftsTable: LongIdTable("aircrafts") {
    val manufacturer: Column<String> = varchar("manufacturer", length = 100)
    val model: Column<String> = varchar("model", length = 100)
    val seats: Column<ULong> = ulong("seats")
}