package repository.model

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Column

object AirportsTable: LongIdTable("airports") {
    val name: Column<String> = varchar("name", length = 3)
    val city: Column<String> = varchar("city", length = 100)
    val country: Column<String> = varchar("country", length = 100)
}