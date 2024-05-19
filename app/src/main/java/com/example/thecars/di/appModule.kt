package com.example.thecars.di

import com.example.thecars.classes.Car
import com.example.thecars.data.MainDb
import com.example.thecars.model.CarDetailsViewModel
import com.example.thecars.model.FavoritesViewModel
import com.example.thecars.repositories.CarsRepository
import com.example.thecars.usecases.ObserveAllCarsUseCase
import com.example.thecars.usecases.RemoveCarFromFavouritesUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    single<MainDb> { MainDb.createDatabase(androidContext()) }
    single { CarsRepository(get()) }

    viewModel<CarDetailsViewModel> { (selectedCar: Car) -> CarDetailsViewModel(get(), selectedCar) }
    viewModel<FavoritesViewModel> { FavoritesViewModel(get(), get()) }

    //Usecases
    factory { ObserveAllCarsUseCase(get()) }
    factory { RemoveCarFromFavouritesUseCase(get()) }
}
