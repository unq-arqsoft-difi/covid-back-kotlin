package difi.covid

import difi.covid.exceptions.ExistsUserException

class CovidApp() {
    val users = mutableListOf<User>()
    val institutions = mutableListOf<Institution>()
    val locations = mutableListOf<Location>()

    fun addUser(user: User): Boolean {
        if (users.any { it.email == user.email }) throw ExistsUserException(user)
        return users.add(user)
    }
}
