import api.authApi
import api.farawayApi
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.typesafe.config.ConfigFactory
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.*
import io.ktor.server.plugins.cors.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.ktor.plugin.Koin
import repository.model.*

fun main(args: Array<String>) {
    embeddedServer(Netty, port = 8080) {
        install(CORS) {
            allowMethod(HttpMethod.Options)
            allowHost("localhost:3000")
        }
        configureDatabase()
        configureServer()
        authApi()
        farawayApi()
    }.start(wait = true)
}

fun Application.configureServer() {
    install(Koin) {
        modules(farawayModule, authModule)
    }
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            ignoreUnknownKeys = true
        })
    }

    val config = ConfigFactory.load("application.conf")
    val secret = config.getString("authorization.secret")
    val issuer = config.getString("authorization.issuer")
    install(Authentication) {
        jwt("access") {
            verifier {
                JWT.require(Algorithm.HMAC256(secret)).withIssuer(issuer).build()
            }

            validate { token ->
                if (token.payload.expiresAt.time > System.currentTimeMillis()) {
                    JWTPrincipal(token.payload)
                } else {
                    null
                }
            }

            challenge { _, _ ->
                call.respond(HttpStatusCode.Unauthorized)
            }
        }
    }
}

private fun configureDatabase() {
    val config = ConfigFactory.load("application.conf")
    val url = config.getString("database.url")
    val username = config.getString("database.username")
    val password = config.getString("database.password")
    val database = Database.connect(url = url, user = username, password = password)
    database.initSchema()
}

private fun Database.initSchema() {
    transaction(this) {
        SchemaUtils.create(UsersTable)
        SchemaUtils.create(TokensTable)

        SchemaUtils.create(OffersTable)
        SchemaUtils.create(AirportsTable)
        SchemaUtils.create(AircraftsTable)
        SchemaUtils.create(RoutesTable)
        SchemaUtils.create(FlightsTable)
        SchemaUtils.create(TicketsTable)
    }
}
