package repository.impl

import model.Offer
import org.jetbrains.exposed.sql.transactions.transaction
import repository.OffersRepository
import repository.model.OfferEntity
import repository.model.OffersTable

class DataBaseOffersRepository: OffersRepository {
    override fun getByCity(departureCity: String): Collection<Offer> {
        return transaction {
            val entities = OfferEntity.find { OffersTable.city eq departureCity }
            entities.map { it.toOffer() }
        }
    }

    private fun OfferEntity.toOffer(): Offer {
        return Offer(
            id = id.value,
            city = city,
            photoUrl = photoUrl,
            departureDate = departureDate,
            arrivalDate = arrivalDate,
            price = price
        )
    }
}