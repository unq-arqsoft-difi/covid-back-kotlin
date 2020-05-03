package difi.covid.api

import difi.covid.CovidApp
import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.core.util.RouteOverviewPlugin
import io.javalin.Javalin

class CovidAPI(private val port: Int) {

    fun startWith(app: CovidApp): Javalin {
        val api = Javalin.create {
            it.enableCorsForAllOrigins()
            it.registerPlugin(RouteOverviewPlugin("/routes"))
        }.exception(Exception::class.java) { e, ctx ->
            e.printStackTrace()
            ctx.status(500)
            ctx.json("Error fatal")
        }.start(port)

        val userController = UserController()
        val locationController = LocationController(app)

        api.routes {
            path("registry") {
                post(userController::registry)
            }
            path("login") {
                post(userController::login)
            }
            path("locations") {
                get(locationController::getAll)
            }
        }

        return api
    }
}
