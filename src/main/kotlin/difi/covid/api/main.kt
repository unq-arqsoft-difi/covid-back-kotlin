@file:JvmName("Main")

package difi.covid.api

import difi.covid.database.UserTable
import difi.covid.support.bootstrapApp
import io.github.cdimascio.dotenv.dotenv
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

fun main() {
    val db: String = dotenv()["DB_FILE"] ?: "/data/data.db"
    Database.connect("jdbc:sqlite:$db", "org.sqlite.JDBC")
    transaction {
        addLogger(StdOutSqlLogger)
        SchemaUtils.create(UserTable)
    }

    CovidAPI(7000).startWith(bootstrapApp())
}
