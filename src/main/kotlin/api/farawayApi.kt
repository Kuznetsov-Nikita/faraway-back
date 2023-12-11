package api

import api.utils.getPathParameter
import api.utils.getUrlParameter
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.koin.ktor.ext.inject
import repository.OffersRepository
import repository.TicketsRepository

fun Application.farawayApi() {
    routing {
        val offersRepository by inject<OffersRepository>()
        val ticketsRepository by inject<TicketsRepository>()

        get("/offers/{dep_city}") {
            val depCity = getPathParameter("dep_city")

            // TODO: update logic for dep_city
            //if (depCity == null) {
            //    call.respond(HttpStatusCode.BadRequest.description("invalid city of origin"))
            //    return@get
            //}

            try {
                val offers = offersRepository.getAll()
                call.respond(offers)
            } catch (e: ExposedSQLException) {
                call.respond(HttpStatusCode.InternalServerError)
            }
        }

        authenticate("access") {
            get("/tickets/{uid}") {
                val userId = getPathParameter("uid")?.toLong()
                if (userId == null) {
                    call.respond(HttpStatusCode.BadRequest.description("invalid user id"))
                    return@get
                }

                try {
                    val tickets = ticketsRepository.getByUser(userId)
                    call.respond(tickets)
                } catch (e: ExposedSQLException) {
                    call.respond(HttpStatusCode.InternalServerError)
                }
            }

            post("/ticket/{id}/buy") {
                val ticketId = getPathParameter("id")?.toLong()
                if (ticketId == null) {
                    call.respond(HttpStatusCode.BadRequest.description("invalid ticket id"))
                    return@post
                }
                val userId = getUrlParameter("uid")?.toLong()
                if (userId == null) {
                    call.respond(HttpStatusCode.BadRequest.description("invalid user id"))
                    return@post
                }

                try {
                    val status = ticketsRepository.buy(userId, ticketId)
                    if (status) {
                        call.respond(HttpStatusCode.OK)
                    } else {
                        call.respond(HttpStatusCode.NotFound)
                    }
                } catch (e: ExposedSQLException) {
                    call.respond(HttpStatusCode.InternalServerError)
                }
            }
        }

        get("/search") {
            val from = getUrlParameter("from")
            val to = getUrlParameter("to")
            if (from == null || to == null) {
                call.respond(HttpStatusCode.BadRequest
                    .description("invalid origin or destination airport code"))
                return@get
            }
            val date = getUrlParameter("date")
            val seatClass = getUrlParameter("class")

            try {
                val tickets = ticketsRepository.search(from.uppercase(), to.uppercase(), date, seatClass?.uppercase())
                call.respond(tickets)
            } catch (e: ExposedSQLException) {
                call.respond(HttpStatusCode.InternalServerError)
            }
        }
    }
}
