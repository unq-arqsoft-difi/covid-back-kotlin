package difi.covid.api

import io.javalin.http.Context

class UserController {

    /**
     * User Registry
     *
     * response:
     *   - 201 & { "created": true }
     *   - 400 & { "created": false, "errors": [ ... ] }
     */
    fun registry(ctx: Context) {
        ctx.status(201)
        ctx.json(mapOf("created" to true))
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
