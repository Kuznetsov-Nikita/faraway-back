package repository.model

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Column

object RoutesTable: LongIdTable("routes") {
    val origin: Column<EntityID<Long>> = reference("origin", AirportsTable)
    val destination: Column<EntityID<Long>> = reference("destination", AirportsTable)
    val durationMinutes: Column<ULong> = ulong("duration_minutes")
}