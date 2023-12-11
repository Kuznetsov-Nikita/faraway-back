package repository

import model.Offer

interface OffersRepository {
    fun getAll(): Collection<Offer>

    fun getByCity(departureCity: String): Collection<Offer>
}