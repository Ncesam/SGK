package com.ncesam.sgk2026.di

import com.ncesam.sgk2026.domain.useCases.*
import org.koin.dsl.module


val UseCaseModule = module {
	factory { LoginUseCase(get(), get()) }
	factory { RefreshTokenUseCase(get(), get()) }
	factory { RegisterUseCase(get()) }
}