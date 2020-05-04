package difi.covid.api

import com.github.kittinunf.fuel.*
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.github.kittinunf.fuel.jackson.responseObject
import difi.covid.api.parsers.BasicUserParser
import difi.covid.api.parsers.RegistryCreationUserParser
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*

class UserControllerTest : APITestCase() {
    @Test
    @Order(1)
    fun `1) GET empty users list`() {
        val (_, response, result) = Fuel.get("users").responseObject<List<BasicUserParser>>()
        assertEquals(200, response.statusCode)
        assertEquals(0, result.get().size)
    }

    @Test
    @Order(2)
    fun `2) POST registration`() {
        val userJson = """
        {
            "firstName": "Jon",
            "lastName": "Snow",
            "email": "jon.snow@winterfell.com",
            "phoneNumber": "+54 11 4444-5555",
            "institution": "Hospital Alemán",
            "role": "Enfermero",
            "location": {
                "province": "Ciudad Autónoma de Buenos Aires",
                "name": "MATADEROS"
            },
            "password": "1234"
        }
        """
        val (_, response, result) = Fuel.post("registry")
            .jsonBody(userJson)
            .responseObject<RegistryCreationUserParser>()
        assertEquals(201, response.statusCode)
        assertTrue(result.get().created)
    }

    @Test
    @Order(3)
    fun `3) POST invalid registration`() {
        val userJson = """
        {
            "firstName": "Jon",
            "lastName": "Snow",
            "password": "1234"
        }
        """
        val (_, response, _) = Fuel.post("registry").jsonBody(userJson).responseObject<RegistryCreationUserParser>()
        assertEquals(400, response.statusCode)
    }

    @Test
    @Order(4)
    fun `4) GET users with recent registration`() {
        val (_, response, result) = Fuel.get("users").responseObject<List<BasicUserParser>>()
        assertEquals(200, response.statusCode)
        assertEquals(1, result.get().size)
        assertEquals("jon.snow@winterfell.com", result.get().first().email)
    }
}
