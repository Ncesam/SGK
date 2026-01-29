package com.ncesam.sgk2026.di

import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit


val NetworkModule = module {
	single { OkHttpClient.Builder().build() }


	single {
		Retrofit.Builder().baseUrl("http://localhost:8089/").client(get()).build()
	}
}