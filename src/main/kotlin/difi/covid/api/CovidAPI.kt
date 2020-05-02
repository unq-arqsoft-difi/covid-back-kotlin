package difi.covid.api

import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.core.util.RouteOverviewPlugin
import io.javalin.Javalin

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
                post(userController::registry)
            }
            path("login") {
                post(userController::login)
            }
        }

        return app
    }
}
