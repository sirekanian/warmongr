package com.sirekanian.warmongr.data.remote

/* TODO: 1202468796234411
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.content.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.compression.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*

private const val ENDPOINT = "https://sirekanian.github.io/warmongr"
private const val DATA_URL = "$ENDPOINT/data.json"
private const val META_URL = "$ENDPOINT/meta.json"

private val httpClient = HttpClient {
    install(ContentNegotiation) {
        json()
    }
    install(ContentEncoding) {
        gzip()
    }
}

suspend fun getWarmongers(listener: ProgressListener): List<WarmongerDto> =
    httpClient.get(DATA_URL) { onDownload(listener) }.body()

suspend fun getMeta(): MetaDto =
    httpClient.get(META_URL).body()
*/

@Suppress("UNUSED_PARAMETER")
fun getWarmongers(listener: (Long, Long) -> Unit): List<WarmongerDto> =
    TODO("1202468796234411")
