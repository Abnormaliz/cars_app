package com.example.thecars.di

import com.example.thecars.data.MainDb
import com.example.thecars.presentation.vm.BrandsViewModel
import com.example.thecars.presentation.vm.CarDetailsViewModel
import com.example.thecars.presentation.vm.CarViewModel
import com.example.thecars.presentation.vm.FavoritesViewModel
import com.example.thecars.presentation.vm.ModelsViewModel
import com.example.thecars.data.repository.CarsRepositoryImpl
import com.example.thecars.data.usecase.AddCarToDatabaseUseCaseImpl
import com.example.thecars.data.usecase.AddNoteToDatabaseUseCaseImpl
import com.example.thecars.data.usecase.CheckCarUseCaseImpl
import com.example.thecars.data.usecase.GetNoteByNameUseCaseImpl
import com.example.thecars.data.usecase.ObserveAllCarsUseCaseImpl
import com.example.thecars.data.usecase.RemoveCarFromDatabaseUseCaseImpl
import com.example.thecars.data.usecase.RemoveCarFromFavouritesUseCaseImpl
import com.example.thecars.data.usecase.RemoveNoteFromDatabaseUseCaseImpl
import com.example.thecars.presentation.models.BrandUi
import com.example.thecars.presentation.models.CarUi
import com.example.thecars.presentation.models.ModelUi
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    single<MainDb> { MainDb.createDatabase(androidContext()) }
    single { CarsRepositoryImpl(get()) }

    viewModel<CarDetailsViewModel> { (selectedCar: CarUi) -> CarDetailsViewModel(selectedCar, get(), get(), get(), get(), get(), get()) }
    viewModel<FavoritesViewModel> { FavoritesViewModel(get(), get()) }
    viewModel<BrandsViewModel> { BrandsViewModel() }
    viewModel<ModelsViewModel> { (selectedBrand: BrandUi) -> ModelsViewModel(selectedBrand) }
    viewModel<CarViewModel> { (selectedModel: ModelUi) -> CarViewModel(selectedModel) }

    //Usecases
    factory { ObserveAllCarsUseCaseImpl(get()) }
    factory { RemoveCarFromFavouritesUseCaseImpl(get()) }
    factory { RemoveCarFromDatabaseUseCaseImpl(get()) }
    factory { AddCarToDatabaseUseCaseImpl(get()) }
    factory { AddNoteToDatabaseUseCaseImpl(get()) }
    factory { RemoveNoteFromDatabaseUseCaseImpl(get()) }
    factory { CheckCarUseCaseImpl(get()) }
    factory { GetNoteByNameUseCaseImpl(get()) }
}
