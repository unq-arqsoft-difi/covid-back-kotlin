package difi.covid.support

import difi.covid.User

object UserBuilder {
    var firstName = "Jon"
    var lastName  = "Snow"
    var email  = "jon.snow@winterfell.com"
    var phoneNumber = "+54 11 4444-5555"
    var institution = InstitutionBuilder.anyOne()
    var role = RoleBuilder.anyOne()
    var location = LocationBuilder.anyOne()

    fun anyOne() = User(firstName, lastName, email, phoneNumber, institution, role, location)

}
