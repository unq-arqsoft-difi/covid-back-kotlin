package difi.covid.support

import difi.covid.Role

object RoleBuilder {
    private const val name = "Lord Commander"
    fun anyOne() = Role(name)
}
