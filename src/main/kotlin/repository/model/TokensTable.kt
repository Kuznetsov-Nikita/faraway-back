package repository.model

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Column

object TokensTable: LongIdTable("tokens") {
    val userId: Column<EntityID<Long>> = reference("user_id", UsersTable)
    var refreshToken: Column<String> = varchar("refresh_token", length = 500)
    var expiresAt: Column<Long> = long("expires_at")
}