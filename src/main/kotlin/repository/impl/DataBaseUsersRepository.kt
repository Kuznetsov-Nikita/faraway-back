package repository.impl

import model.User
import model.UserInfo
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
    ): UserInfo? {
        return transaction {
            if (getByEmail(email) != null) {
                null
            } else {
                UserEntity.new {
                    this.firstName = firstName
                    this.lastName = lastName
                    this.email = email
                    this.password = password
                    this.city = city
                    this.country = country
                    this.phone = phone
                }.toUserInfo()
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

    override fun update(
        id: Long,
        firstName: String?,
        lastName: String?,
        email: String?,
        city: String?,
        country: String?,
        phone: String?
    ): UserInfo {
        return transaction {
            val user = UserEntity.findById(id)!!

            user.firstName = firstName ?: user.firstName
            user.lastName = lastName ?: user.lastName
            user.email = email ?: user.email
            user.phone = phone ?: user.phone
            user.city = city ?: user.city
            user.country = country ?: user.country

            user.toUserInfo()
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

    private fun UserEntity.toUserInfo(): UserInfo {
        return UserInfo(
            id = id.value,
            firstName = firstName,
            lastName = lastName,
            phone = phone,
            email = email,
            city = city,
            country = country,
        )
    }
}