package difi.covid

data class User(
    val firstName: String,
    val lastName: String,
    val email: String,
    val phoneNumber: String,
    val institution: Institution,
    val role: Role,
    val location: Location
) {
    fun fullName(): String = "$firstName $lastName"
}
