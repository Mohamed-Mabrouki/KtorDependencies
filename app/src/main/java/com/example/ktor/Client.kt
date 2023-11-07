package com.example.ktor

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import java.net.InetSocketAddress
import java.net.Proxy

class Client  {
	val client = HttpClient(Android) {
		engine {
			// this: AndroidEngineConfig
			proxy = Proxy(Proxy.Type.HTTP, InetSocketAddress("localhost", 8080))
		}
	}

}