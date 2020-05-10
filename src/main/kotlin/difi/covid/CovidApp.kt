package difi.covid

import difi.covid.database.UserTable
import difi.covid.database.toUser
import difi.covid.exceptions.ExistsUserException
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class CovidApp(
    val users: MutableList<User> = mutableListOf(),
    val institutions: MutableList<Institution> = mutableListOf(),
    val locations: MutableList<Location> = mutableListOf()
) {

    fun addUser(user: User): Boolean {
        if (users.any { it.email == user.email }) throw ExistsUserException(user)

        transaction {
            UserTable.insert {
                it[firstName] = user.firstName
                it[lastName] = user.lastName
                it[email] = user.email
                it[phoneNumber] = user.phoneNumber
                it[institution] = "${user.institution.name}:${user.institution.location.name}"
                it[role] = user.role.name ?: ""
                it[location] = user.location.name
            }
        }

        return users.add(user)
    }

    fun dbUsers(): List<User> {
        return transaction {
            UserTable.selectAll().map { it.toUser() }
        }
    }
}
