package difi.covid.support

import difi.covid.Institution

object InstitutionBuilder {
    var name = "Instituto Malbrán"
    var location = LocationBuilder.anyOne()

    fun anyOne() = Institution(name, location)
}
