package difi.covid.support

import com.fasterxml.jackson.module.kotlin.*
import difi.covid.CovidApp
import difi.covid.Location
import java.io.File
import java.net.URL

fun parseTowns(): JsonTownListParser {
    val json = "https://infra.datos.gob.ar/catalog/modernizacion/dataset/7/distribution/7.5/download/localidades.json"
    val mapper = jacksonObjectMapper()
    return mapper.readValue(URL(json))
}

fun bootstrapApp(): CovidApp {
    val locations = parseTowns().localidades!!.map { Location(
        name = it.nombre!!,
        province = it.provincia!!["nombre"] ?: ""
    ) }.toMutableList()
    return CovidApp(locations = locations)
}

data class JsonTownParser(
    val categoria: String? = null,
    val fuente: String? = null,
    val municipio: Map<String, String>? = null,
    val departamento: Map<String, String>? = null,
    val nombre: String? = null,
    val id: String? = null,
    val provincia: Map<String, String>? = null,
    val localidad_censal: Map<String, String>? = null,
    val centroide: Map<String, Double>? = null
)

data class JsonTownListParser(
    val localidades: List<JsonTownParser>? = null,
    val total: Int? = null,
    val cantidad: Int? = null,
    val parametros: Any? = null,
    val inicio: Int?
)
