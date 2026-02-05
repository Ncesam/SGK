package com.ncesam.sgk2026.di

import com.ncesam.sgk2026.presentation.viewModels.BookingViewModel
import com.ncesam.sgk2026.presentation.viewModels.LoginViewModel
import com.ncesam.sgk2026.presentation.viewModels.MainViewModel
import com.ncesam.sgk2026.presentation.viewModels.RegistrationViewModel
import com.ncesam.sgk2026.presentation.viewModels.SplashViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {
	viewModel { SplashViewModel(get()) }
	viewModel { LoginViewModel(get()) }
	viewModel { RegistrationViewModel(get()) }
	viewModel { MainViewModel(get()) }
	viewModel { BookingViewModel(get()) }
}