package difi.covid.api

import com.github.kittinunf.fuel.*
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.jackson.responseObject
import difi.covid.CovidApp
import io.javalin.Javalin
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class UserControllerTest {
    private lateinit var api: Javalin

    @BeforeAll
    fun setUp() {
        api = CovidAPI(8000).startWith(CovidApp())
        // Inject the base path to no have repeat the whole URL
        FuelManager.instance.basePath = "http://localhost:${api.port()}/"
    }

    @AfterAll
    fun tearDown() {
        api.stop()
    }

    @Test @Order(1)
    fun `1 - POST registration`() {
        val userJson = """
        {
            "firstName": "Jon",
            "lastName": "Snow",
            "email": "jon.snow@winterfell.com",
            "phone": "+54 11 4444-5555",
            "entity": "Hospital Alemán",
            "job": "Enfermero",
            "place": "CABA",
            "pass": "1234"
        }
        """
        val (_, response, result) = Fuel.post("registry").jsonBody(userJson).responseObject<Map<String, Boolean>>()
        assertEquals(201, response.statusCode)
        assertTrue(result.get().getOrElse("created") { false })
    }
}
