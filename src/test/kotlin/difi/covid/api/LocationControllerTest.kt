package difi.covid.api

import com.github.kittinunf.fuel.*
import com.github.kittinunf.fuel.jackson.responseObject
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*

class LocationControllerTest : APITestCase() {

    @Test
    @Order(1)
    fun `1) GET empty locations`() {
        val (_, response, result) = Fuel.get("locations").responseObject<Map<String, List<String>>>()
        assertEquals(200, response.statusCode)
        assertEquals(0, result.get().size)
    }
}
