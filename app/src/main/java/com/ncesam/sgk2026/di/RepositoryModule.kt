package com.ncesam.sgk2026.di

import com.ncesam.sgk2026.data.repositories.UserSettingsRepositoryImpl
import com.ncesam.sgk2026.data.repositories.AuthRepositoryImpl
import com.ncesam.sgk2026.data.repositories.HotelRepositoryImpl
import com.ncesam.sgk2026.data.repositories.TokenManagerImpl
import com.ncesam.sgk2026.domain.repositories.UserSettingsRepository
import com.ncesam.sgk2026.domain.repositories.AuthRepository
import com.ncesam.sgk2026.domain.repositories.HotelRepository
import com.ncesam.sgk2026.domain.repositories.TokenManager
import org.koin.dsl.module


val RepositoryModule = module {
	single<AuthRepository> { AuthRepositoryImpl(get()) }
	single<UserSettingsRepository> { UserSettingsRepositoryImpl(get()) }
	single<TokenManager> { TokenManagerImpl(get(), get()) }
	single<HotelRepository> { HotelRepositoryImpl(get(), get()) }
}