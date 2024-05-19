package com.example.thecars.di

import com.example.thecars.classes.Car
import com.example.thecars.data.MainDb
import com.example.thecars.model.CarDetailsViewModel
import com.example.thecars.model.FavoritesViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    single<MainDb> { MainDb.createDatabase(androidContext()) }
//    factory {  }
    viewModel<CarDetailsViewModel> { (selectedCar: Car) -> CarDetailsViewModel(get(), selectedCar) }
    viewModel<FavoritesViewModel> { (FavoritesViewModel(get())) }
}
