package com.ncesam.sgk2026.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit

const val BASE_URL = "http://127.0.0.1:8090/"
val json = Json {
	ignoreUnknownKeys = true
}

val NetworkModule = module {
	single {
		OkHttpClient.Builder()
			.apply {
				val logging = HttpLoggingInterceptor().apply {
					level = HttpLoggingInterceptor.Level.BASIC
					redactHeader("Authorization")
				}
				addInterceptor(logging)
			}
			.build()
	}


	single {
		Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
			json.asConverterFactory("application/json".toMediaType())
		).client(get()).build()
	}
}