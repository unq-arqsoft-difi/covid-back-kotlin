package difi.covid.api

import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.core.util.RouteOverviewPlugin

class CovidAPI(private val port: Int) {

    fun init(): Javalin {
        val app = Javalin.create {
            it.enableCorsForAllOrigins()
            it.registerPlugin(RouteOverviewPlugin("/routes"))
        }.exception(Exception::class.java) { e, ctx ->
            e.printStackTrace()
            ctx.status(500)
            ctx.json("Error fatal")
        }.start(port)

        val userController = UserController()

        app.routes {
            path("registry") {
                post(userController::registry)  // POST /registry
            }
            path("login") {
                post(userController::login)     // POST /login
            }
        }

        return app
    }
}
