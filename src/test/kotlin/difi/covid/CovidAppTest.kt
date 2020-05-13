package difi.covid

import difi.covid.database.UserTable
import difi.covid.exceptions.ExistsUserException
import difi.covid.support.UserBuilder
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CovidAppTest {
    lateinit var app: CovidApp

    @BeforeAll
    fun initAll() {
        Database.connect("jdbc:sqlite:file:test", "org.sqlite.JDBC")
        transaction {
            addLogger(StdOutSqlLogger)
            SchemaUtils.create(UserTable)
        }
    }

    @AfterAll
    fun cleanAll() {
        transaction {
            addLogger(StdOutSqlLogger)
            SchemaUtils.drop(UserTable)
        }
    }

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
