package repository

import model.User
import model.UserInfo

interface UsersRepository {
    fun addUser(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        city: String,
        country: String,
        phone: String? = null,
    ): UserInfo?

    fun getByEmail(email: String): User?

    fun update(
        id: Long,
        firstName: String? = null,
        lastName: String? = null,
        email: String? = null,
        city: String? = null,
        country: String? = null,
        phone: String? = null,
    ): UserInfo
}