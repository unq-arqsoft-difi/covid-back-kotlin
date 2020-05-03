package difi.covid

class CovidApp() {
    val users = mutableListOf<User>()
    val institutions = mutableListOf<Institution>()
    val locations = mutableListOf<Location>()

    fun addUser(user: User) = users.add(user)
}
