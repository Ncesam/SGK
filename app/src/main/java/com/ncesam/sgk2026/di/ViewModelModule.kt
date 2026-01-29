package com.ncesam.sgk2026.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import com.ncesam.sgk2026.presentation.viewModels.SplashViewModel
import com.ncesam.sgk2026.presentation.viewModels.LoginViewModel
import com.ncesam.sgk2026.presentation.viewModels.RegistrationViewModel

val ViewModelModule = module {
	viewModel { SplashViewModel(get()) }
	viewModel { LoginViewModel(get()) }
	viewModel { RegistrationViewModel(get()) }
}