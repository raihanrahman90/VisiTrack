package com.visitrack.di

import com.visitrack.core.domain.usecase.Interactor
import com.visitrack.core.domain.usecase.UseCase
import com.visitrack.main.MainViewModel
import com.visitrack.start.viewmodel.LoginViewModel
import com.visitrack.start.viewmodel.RegisterViewModel
import com.visitrack.violation.DetailNotificationViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<UseCase> { Interactor(get()) }
}

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { MainViewModel(get()) }
    viewModel { DetailNotificationViewModel(get()) }
}