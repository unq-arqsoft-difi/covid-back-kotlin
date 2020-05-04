package difi.covid.api.parsers

import difi.covid.Institution
import difi.covid.Location
import difi.covid.Role
import difi.covid.User

class RegistryUserParser(
    val firstName: String? = null,
    val lastName: String? = null,
    val email: String? = null,
    val phoneNumber: String? = null,
    val institution: String? = null,
    val role: String? = null,
    val location: Map<String?, String?>? = null,
    val password: String? = null
) {
    fun asUser() = User(
        firstName = firstName!!,
        lastName = lastName!!,
        email = email!!,
        phoneNumber = phoneNumber!!,
        institution = Institution(institution!!, Location("", "")), // FIXME
        role = Role(role!!),
        location = Location(location!!.getOrElse("name") { "a" }!!, location.getOrElse("province") { "b" })
    )
}
