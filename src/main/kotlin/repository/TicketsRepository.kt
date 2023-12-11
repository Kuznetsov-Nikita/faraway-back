package repository

import model.Ticket

interface TicketsRepository {
    fun getByUser(userId: Long): Collection<Ticket>

    fun search(from: String, to: String, date: String? = null, seatClass: String? = null): Collection<Ticket>

    fun buy(userId: Long, ticketId: Long): Boolean
}