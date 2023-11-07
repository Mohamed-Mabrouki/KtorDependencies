package com.example.ktor

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.ktor.ui.theme.KtorTheme
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.serializer

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.InetSocketAddress
import java.net.Proxy


val client = HttpClient(Android).config {
	install(ContentNegotiation) {
		json()
	}
}

class MainActivity : ComponentActivity() {
	@OptIn(DelicateCoroutinesApi::class)
	@SuppressLint("CoroutineCreationDuringComposition")
	override fun onCreate(savedInstanceState : Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			KtorTheme { // A surface container using the 'background' color from the theme
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background,
				) {
					val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
						throwable.printStackTrace()
					}
					GlobalScope.launch {
						withContext(Dispatchers.IO) {
							val response : HttpResponse = client.get("https://jsonplaceholder" +
								".typicode.com/posts")
							Log.d("my response", "${response.status}")
						}
					}
				}
			}
		}
	}
}

//suspend fun request() {
//	val client = HttpClient()
//	val response : HttpResponse = client.get("https://jsonplaceholder.typicode.com/posts")
//	Log.d("my response", "${response.status}")
//	client.close()
//
//}



