package difi.covid.database

import org.jetbrains.exposed.sql.Table

object UserTable : Table("users") {
    val id = integer("id").autoIncrement()
    val firstName = varchar("first_name", 255)
    val lastName = varchar("last_name", 255)
    val email = varchar("email", 255)
    val phoneNumber = varchar("phone_number", 255).nullable()
    val institution = varchar("institution", 255)
    val role = varchar("role", 255).nullable()
    val location = varchar("location", 255)
    val password = varchar("password", 255).nullable()

    override val primaryKey = PrimaryKey(id)
}
