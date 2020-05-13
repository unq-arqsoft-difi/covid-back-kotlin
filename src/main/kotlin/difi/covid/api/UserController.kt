package difi.covid.api

import difi.covid.CovidApp
import difi.covid.api.parsers.BasicUserParser
import difi.covid.api.parsers.RegistryCreationUserParser
import difi.covid.api.parsers.RegistryUserParser
import difi.covid.exceptions.ExistsUserException
import io.javalin.http.Context

class UserController(private val app: CovidApp) {

    fun getAll(ctx: Context) {
        val users = app.dbUsers().map { BasicUserParser(it) }
        ctx.status(200)
        ctx.json(users)
    }

    /**
     * User Registry
     *
     * response:
     *   - 201 & { "created": true }
     *   - 400 & { "created": false, "errors": [ ... ] }
     */
    fun registry(ctx: Context) {
        try {
            val parsedUser = ctx.bodyValidator<RegistryUserParser>()
                .check({ nonEmptyFields(it) }, "Invalid json body (FIXME better message)")
                .get()
            app.addUser(parsedUser.asUser())
            ctx.status(201)
            ctx.json(RegistryCreationUserParser(created = true))
        } catch (ex: ExistsUserException) {
            ctx.status(400)
            ctx.json(RegistryCreationUserParser(
                created = false,
                errors = listOf(ex.message!!)
            ))
        }
    }

    private fun nonEmptyFields(userParser: RegistryUserParser): Boolean {
        return userParser.firstName != null &&
            userParser.lastName != null &&
            userParser.email != null &&
            userParser.phoneNumber != null &&
            userParser.institution != null &&
            userParser.role != null &&
            userParser.location != null &&
            userParser.password != null
    }

    /**
     * User Login
     *
     * response:
     *   - 200 & { "token": "1234567890" }
     *   - 400 & { "token": false, "errors": ["Invalid email or password"] }
     */
    fun login(ctx: Context) {
        ctx.status(200)
        ctx.json(mapOf("token" to "1234567890"))
    }
}
