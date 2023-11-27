package repository.model

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Column

object UsersTable: LongIdTable("users") {
    var firstName: Column<String> = varchar("firstname", length = 100)
    var lastName: Column<String> = varchar("lastname", length = 100)
    var phone: Column<String?> = varchar("phone", length = 100).nullable()
    var email: Column<String> = varchar("email", length = 100)
    var password: Column<String> = varchar("password", length = 100)
    var city: Column<String> = varchar("city", length = 100)
    var country: Column<String> = varchar("country", length = 100)
}