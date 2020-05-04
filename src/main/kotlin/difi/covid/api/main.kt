package difi.covid.api

import difi.covid.support.bootstrapApp

fun main() {
    CovidAPI(7000).startWith(bootstrapApp())
}
