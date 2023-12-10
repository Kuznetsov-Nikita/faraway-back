package api

import api.model.LoginRequest
import api.model.RegisterRequest
import api.model.CustomerRequest
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import model.RefreshToken
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.koin.ktor.ext.inject
import repository.TokensRepository
import repository.UsersRepository

fun Application.authApi() {
    routing {
        val usersRepository by inject<UsersRepository>()
        val tokensRepository by inject<TokensRepository>()

        post("/register") {
            val request = call.receive<RegisterRequest>()

            try {
                val userInfo = usersRepository.addUser(
                    request.firstName,
                    request.lastName,
                    request.email,
                    request.password,
                    request.city,
                    request.country,
                    request.phone,
                )

                if (userInfo != null) {
                    call.respond(userInfo)
                } else {
                    call.respond(HttpStatusCode.Conflict)
                }
            } catch (e: ExposedSQLException) {
                call.respond(HttpStatusCode.InternalServerError)
            }
        }

        post("/login") {
            val request = call.receive<LoginRequest>()

            try {
                val user = usersRepository.getByEmail(request.email)

                if (user == null || user.password != request.password) {
                    call.respond(HttpStatusCode.Unauthorized)
                } else {
                    val tokenPair = tokensRepository.generateTokenPair(user.id)
                    call.respond(tokenPair)
                }
            } catch (e: ExposedSQLException) {
                call.respond(HttpStatusCode.InternalServerError)
            }
        }

        authenticate("access") {
            patch("/customer") {
                val request = call.receive<CustomerRequest>()

                try {
                    val userInfo = usersRepository.update(
                        request.id,
                        request.firstName,
                        request.lastName,
                        request.email,
                        request.city,
                        request.country,
                        request.phone,
                    )
                    call.respond(userInfo)
                } catch (e: ExposedSQLException) {
                    call.respond(HttpStatusCode.InternalServerError)
                }
            }
        }

        post("/refresh") {
            val oldRefreshToken = call.receive<RefreshToken>().refreshToken

            try {
                val refreshTokenInfo = tokensRepository.getRefreshTokenInfo(oldRefreshToken)
                val currentTime = System.currentTimeMillis()

                if (refreshTokenInfo == null || refreshTokenInfo.expiresAt < currentTime) {
                    call.respond(HttpStatusCode.Forbidden)
                } else {
                    val tokenPair = tokensRepository.generateTokenPair(refreshTokenInfo.userId, true)
                    tokensRepository.updateRefreshToken(oldRefreshToken, tokenPair.refreshToken)
                    call.respond(tokenPair)
                }
            } catch (e: ExposedSQLException) {
                call.respond(HttpStatusCode.InternalServerError)
            }
        }
    }
}
