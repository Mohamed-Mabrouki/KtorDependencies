package com.example.ktor

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import java.net.InetSocketAddress
import java.net.Proxy

class Client {
	val client = HttpClient(Android).config {
		install(ContentNegotiation) {
			json(
				Json {
					ignoreUnknownKeys = true

					isLenient = true

					encodeDefaults = true
				},
			)
		}

		install(Logging) {
			logger = Logger.SIMPLE

			level = LogLevel.ALL
		}
	}
}
