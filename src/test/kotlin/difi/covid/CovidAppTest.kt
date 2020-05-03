package difi.covid

import difi.covid.exceptions.ExistsUserException
import difi.covid.support.UserBuilder
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CovidAppTest {
    lateinit var app: CovidApp

    @BeforeEach fun setUp() {
        app = CovidApp()
    }

    @Test
    fun `add user`() {
        assertEquals(0, app.users.size)
        val user = UserBuilder.anyOne()
        app.addUser(user)
        assertEquals(1, app.users.size)
        assertEquals(user.email, app.users.first().email)
    }

    @Test
    fun `cannot add user with same email that another previously added`() {
        assertEquals(0, app.users.size)

        val firstUser = UserBuilder.with(email = "first@user.com")
        val secondUser = UserBuilder.with(email = "second@user.com")
        val thirdUser = UserBuilder.with(email = "first@user.com")

        app.addUser(firstUser)
        app.addUser(secondUser)
        assertEquals(2, app.users.size)

        assertThrows<ExistsUserException> {
            app.addUser(thirdUser)
        }
    }
}
