package repository.model

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class UserEntity(id: EntityID<Long>): LongEntity(id) {
    companion object: LongEntityClass<UserEntity>(UsersTable)

    var firstName by UsersTable.firstName
    var lastName by UsersTable.lastName
    var phone by UsersTable.phone
    var email by UsersTable.email
    var password by UsersTable.password
    var city by UsersTable.city
    var country by UsersTable.country
}