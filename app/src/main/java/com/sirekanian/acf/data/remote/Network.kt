package com.sirekanian.acf.data.remote

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.content.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.compression.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*

private const val ACF_URL = "https://sirekanian.github.io/warmongers.json"
private val httpClient = HttpClient {
    install(ContentNegotiation) {
        json()
    }
    install(ContentEncoding) {
        gzip()
    }
}

suspend fun getWarmongers(listener: ProgressListener): List<WarmongerDto> =
    httpClient.get(ACF_URL) { onDownload(listener) }.body()
