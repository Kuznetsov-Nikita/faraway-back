package repository.impl

import model.*
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import repository.TicketsRepository
import repository.model.*

class DataBaseTicketsRepository: TicketsRepository {
    override fun getByUser(userId: Long): Collection<Ticket> {
        return transaction {
            val entities = TicketEntity.find { TicketsTable.owner eq userId }
            entities.map { it.toTicket() }
        }
    }

    override fun search(from: String, to: String, date: String?): Collection<Ticket> {
        return transaction {
            val fromId: Long
            val toId: Long
            try {
                fromId = AirportEntity.find { AirportsTable.city eq from }.first().id.value
                toId = AirportEntity.find { AirportsTable.city eq to }.first().id.value
            } catch (e: NoSuchElementException) {
                return@transaction listOf<Ticket>()
            }
            val routeIds = RouteEntity
                .find { (RoutesTable.origin eq fromId) and (RoutesTable.destination eq toId) }
                .map { it.id.value }
            val flightIds = if (date.isNullOrEmpty()) {
                FlightEntity.find { FlightsTable.route inList routeIds }.map { it.id.value }
            } else {
                FlightEntity
                    .find { (FlightsTable.route inList routeIds) and (FlightsTable.depTime like "$date%")  }
                    .map { it.id.value }
            }
            val entities = TicketEntity.find { (TicketsTable.flight inList flightIds) and (TicketsTable.owner eq null) }
            entities.map { it.toTicket() }
        }
    }

    override fun buy(userId: Long, ticketId: Long): Boolean {
        return transaction {
            val ticket = TicketEntity.findById(ticketId)
            if (ticket == null) {
                false
            } else {
                val user = UserEntity.findById(userId) ?: return@transaction false
                ticket.owner = user
                true
            }
        }
    }

    private fun AirportEntity.toAirport(): Airport {
        return Airport(
            id = id.value,
            name = name,
            city = city,
            country = country
        )
    }

    private fun AircraftEntity.toAircraft(): Aircraft {
        return Aircraft(
            id = id.value,
            manufacturer = manufacturer,
            model = model,
            seats = seats
        )
    }

    private fun RouteEntity.toRoute(): Route {
        return Route(
            id = id.value,
            origin = origin.toAirport(),
            destination = destination.toAirport(),
            durationMinutes = durationMinutes
        )
    }

    private fun FlightEntity.toFlight(): Flight {
        return Flight(
            id = id.value,
            airline = airline,
            route = route.toRoute(),
            aircraft = aircraft.toAircraft(),
            depTime = depTime
        )
    }

    private fun TicketEntity.toTicket(): Ticket {
        return Ticket(
            id = id.value,
            owner = owner?.id?.value,
            flight = flight.toFlight(),
            seatClass = seatClass,
            price = price,
            luggageKg = luggageKg,
            handLuggageKg = handLuggageKg,
            refundable = refundable
        )
    }
}
