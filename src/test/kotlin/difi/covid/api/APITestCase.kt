package difi.covid.api

import com.github.kittinunf.fuel.core.FuelManager
import difi.covid.CovidApp
import difi.covid.database.UserTable
import io.javalin.Javalin
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
abstract class APITestCase {
    private lateinit var api: Javalin

    @BeforeAll
    fun setUp() {
        Database.connect("jdbc:sqlite:file:test", "org.sqlite.JDBC")
        transaction {
            addLogger(StdOutSqlLogger)
            SchemaUtils.create(UserTable)
        }
        api = CovidAPI(8000).startWith(CovidApp())
        // Inject the base path to no have repeat the whole URL
        FuelManager.instance.basePath = "http://localhost:${api.port()}/"
    }

    @AfterAll
    fun tearDown() {
        api.stop()
        transaction {
            addLogger(StdOutSqlLogger)
            SchemaUtils.drop(UserTable)
        }
    }
}
