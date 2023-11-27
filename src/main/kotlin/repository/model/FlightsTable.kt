package repository.model

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Column

object FlightsTable: LongIdTable("flights") {
    val airline: Column<String> = varchar("airline", length = 100)
    val route: Column<EntityID<Long>> = reference("route", RoutesTable)
    val aircraft: Column<EntityID<Long>> = reference("aircraft", AircraftsTable)
    val depTime: Column<String> = varchar("dep_time", length = 25)
}