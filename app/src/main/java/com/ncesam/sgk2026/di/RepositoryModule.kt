package com.ncesam.sgk2026.di

import com.ncesam.sgk2026.data.repositories.AppSettingsRepositoryImpl
import com.ncesam.sgk2026.data.repositories.AuthRepositoryImpl
import com.ncesam.sgk2026.domain.repositories.AppSettingsRepository
import com.ncesam.sgk2026.domain.repositories.AuthRepository
import org.koin.dsl.module


val RepositoryModule = module {
	single<AuthRepository> { AuthRepositoryImpl(get()) }
	single<AppSettingsRepository> { AppSettingsRepositoryImpl(get()) }
}