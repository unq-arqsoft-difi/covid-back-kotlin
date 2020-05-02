package difi.covid.api

import com.github.kittinunf.fuel.*
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.jackson.responseObject
import io.javalin.Javalin
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class UserControllerTest {
    private lateinit var api: Javalin

    @BeforeAll
    fun setUp() {
        api = CovidAPI(8000).init()
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
            }
        """
        val (_, response, result) = Fuel.post("registry").jsonBody(userJson).responseObject<Map<String, Boolean>>()
        assertEquals(201, response.statusCode)
        assertTrue(result.get().getOrElse("created") { false })
    }
}
