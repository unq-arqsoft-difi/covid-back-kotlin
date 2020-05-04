package difi.covid.support

import difi.covid.Location

object LocationBuilder {
    private const val name = "Winterfell"
    private const val province = "Kingdom of the North"

    fun anyOne() = Location(name, province)
}
