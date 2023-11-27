package repository

import model.User

interface UsersRepository {
    fun addUser(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        city: String,
        country: String,
        phone: String? = null,
    ): Boolean

    fun getByEmail(email: String): User?
}