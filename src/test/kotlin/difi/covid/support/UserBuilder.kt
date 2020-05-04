package difi.covid.support

import difi.covid.Institution
import difi.covid.Location
import difi.covid.Role
import difi.covid.User

object UserBuilder {
    private const val firstName = "Jon"
    private const val lastName = "Snow"
    private const val email = "jon.snow@winterfell.com"
    private const val phoneNumber = "+54 11 4444-5555"
    private val institution = InstitutionBuilder.anyOne()
    private val role = RoleBuilder.anyOne()
    private val location = LocationBuilder.anyOne()

    fun anyOne() = with()

    fun with(
        firstName: String? = null,
        lastName: String? = null,
        email: String? = null,
        phoneNumber: String? = null,
        institution: Institution? = null,
        role: Role? = null,
        location: Location? = null
    ) = User(
        firstName = firstName ?: UserBuilder.firstName,
        lastName = lastName ?: UserBuilder.lastName,
        email = email ?: UserBuilder.email,
        phoneNumber = phoneNumber ?: UserBuilder.phoneNumber,
        institution = institution ?: UserBuilder.institution,
        role = role ?: UserBuilder.role,
        location = location ?: UserBuilder.location
    )
}
