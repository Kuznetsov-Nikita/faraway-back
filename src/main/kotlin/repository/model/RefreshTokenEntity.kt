package repository.model

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class RefreshTokenEntity(id: EntityID<Long>): LongEntity(id) {
    companion object: LongEntityClass<RefreshTokenEntity>(TokensTable)

    var userId by TokensTable.userId
    var refreshToken by TokensTable.refreshToken
    var expiresAt by TokensTable.expiresAt
}