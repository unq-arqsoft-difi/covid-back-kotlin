package difi.covid.api.parsers

import difi.covid.User

data class BasicUserParser(
    val name: String? = null,
    val email: String? = null,
    val institution: String? = null,
    val role: String? = null
) {
    constructor(user: User) : this(
        name = user.fullName(),
        email = user.email,
        institution = user.institution.name,
        role = user.role.name
    )
}
