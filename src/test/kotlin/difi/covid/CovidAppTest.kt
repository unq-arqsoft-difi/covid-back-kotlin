package difi.covid

import difi.covid.support.UserBuilder
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CovidAppTest {
    lateinit var app: CovidApp
    @BeforeEach fun setUp() {
        app = CovidApp()
    }

    @Test fun `add user`() {
        assertEquals(0, app.users.size)
        val user = UserBuilder.anyOne()
        app.addUser(user)
        assertEquals(1, app.users.size)
        assertEquals(user.email, app.users.first().email)
    }
}
