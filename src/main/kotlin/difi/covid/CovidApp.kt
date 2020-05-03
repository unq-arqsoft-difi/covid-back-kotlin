package difi.covid

import difi.covid.exceptions.ExistsUserException

class CovidApp(
    val users: MutableList<User> = mutableListOf(),
    val institutions: MutableList<Institution> = mutableListOf(),
    val locations: MutableList<Location> = mutableListOf()
) {

    fun addUser(user: User): Boolean {
        if (users.any { it.email == user.email }) throw ExistsUserException(user)
        return users.add(user)
    }
}
