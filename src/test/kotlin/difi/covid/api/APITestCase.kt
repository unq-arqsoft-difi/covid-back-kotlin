package difi.covid.api

import com.github.kittinunf.fuel.core.FuelManager
import difi.covid.CovidApp
import io.javalin.Javalin
import org.junit.jupiter.api.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
abstract class APITestCase {
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
}
