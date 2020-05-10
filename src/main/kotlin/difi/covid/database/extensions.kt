package difi.covid.database

import difi.covid.Institution
import difi.covid.Location
import difi.covid.Role
import difi.covid.User
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toUser() = User(
    firstName = this[UserTable.firstName],
    lastName = this[UserTable.lastName],
    email = this[UserTable.email],
    phoneNumber = this[UserTable.phoneNumber] ?: "",
    institution = Institution(this[UserTable.institution], Location(this[UserTable.institution], null)),
    role = Role(this[UserTable.role]),
    location = Location(this[UserTable.location], null)
)
