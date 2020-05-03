package difi.covid.api

import difi.covid.CovidApp
import difi.covid.Location
import io.javalin.http.Context

class LocationController(val app: CovidApp) {
    fun getAll(ctx: Context) {
        val locations = app.locations
            .sortedWith(compareBy<Location> { it.province }.thenBy { it.name })
            .groupBy({ it.province }, { it.name })

        ctx.status(200)
        ctx.json(locations)
    }
}
