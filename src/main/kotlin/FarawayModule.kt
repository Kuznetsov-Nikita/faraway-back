import repository.OffersRepository
import repository.TicketsRepository
import repository.impl.DataBaseOffersRepository
import repository.impl.DataBaseTicketsRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val farawayModule = module {
    singleOf(::DataBaseOffersRepository) bind OffersRepository::class
    singleOf(::DataBaseTicketsRepository) bind TicketsRepository::class
}