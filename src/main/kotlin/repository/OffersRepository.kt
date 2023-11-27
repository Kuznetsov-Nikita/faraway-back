package repository

import model.Offer

interface OffersRepository {
    fun getByCity(departureCity: String): Collection<Offer>
}