package repository.impl

import model.User
import org.jetbrains.exposed.sql.transactions.transaction
import repository.UsersRepository
import repository.model.UserEntity
import repository.model.UsersTable
import kotlin.random.Random

class DataBaseUsersRepository: UsersRepository {
    override fun addUser(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        city: String,
        country: String,
        phone: String?,
    ): Boolean {
        return transaction {
            if (getByEmail(email) != null) {
                false
            } else {
                UserEntity.new {
                    this.firstName = firstName
                    this.lastName = lastName
                    this.email = email
                    this.password = password
                    this.city = city
                    this.country = country
                    this.phone = phone
                }

                true
            }
        }
    }

    override fun getByEmail(email: String): User? {
        return transaction {
            val user = UserEntity.find { UsersTable.email eq email }
            if (user.empty()) {
                null
            } else {
                user.first().toUser()
            }
        }
    }

    private fun UserEntity.toUser(): User {
        return User(
            id = id.value,
            firstName = firstName,
            lastName = lastName,
            phone = phone,
            email = email,
            password = password,
            city = city,
            country = country,
        )
    }
}