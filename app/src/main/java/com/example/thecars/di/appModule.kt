package com.example.thecars.di

import com.example.thecars.app.presentation.models.BrandUi
import com.example.thecars.app.presentation.models.CarUi
import com.example.thecars.app.presentation.models.ModelUi
import com.example.thecars.app.presentation.vm.BrandsViewModel
import com.example.thecars.app.presentation.vm.CarDetailsViewModel
import com.example.thecars.app.presentation.vm.CarViewModel
import com.example.thecars.app.presentation.vm.FavoritesViewModel
import com.example.thecars.app.presentation.vm.ModelsViewModel
import com.example.thecars.data.CarDatabase
import com.example.thecars.data.repositoryImpl.CarsRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    single { CarDatabase.createDatabase(androidContext()) }
    single { CarsRepositoryImpl(get()) }

    viewModel<CarDetailsViewModel> { (selectedCar: CarUi) -> CarDetailsViewModel(selectedCar, get(), get(), get(), get(), get(), get()) }
    viewModel<FavoritesViewModel> { FavoritesViewModel(get(), get()) }
    viewModel<BrandsViewModel> { BrandsViewModel() }
    viewModel<ModelsViewModel> { (selectedBrand: BrandUi) -> ModelsViewModel(selectedBrand) }
    viewModel<CarViewModel> { (selectedModel: ModelUi) -> CarViewModel(selectedModel) }

    //Usecases
    factory { com.example.thecars.domain.usecaseImpl.ObserveAllCarsUseCaseImpl(get()) }
    factory { com.example.thecars.domain.usecaseImpl.RemoveCarFromFavouritesUseCaseImpl(get()) }
    factory { com.example.thecars.domain.usecaseImpl.RemoveCarFromDatabaseUseCaseImpl(get()) }
    factory { com.example.thecars.domain.usecaseImpl.AddCarToDatabaseUseCaseImpl(get()) }
    factory { com.example.thecars.domain.usecaseImpl.AddNoteToDatabaseUseCaseImpl(get()) }
    factory { com.example.thecars.domain.usecaseImpl.RemoveNoteFromDatabaseUseCaseImpl(get()) }
    factory { com.example.thecars.domain.usecaseImpl.CheckCarUseCaseImpl(get()) }
    factory { com.example.thecars.domain.usecaseImpl.GetNoteByNameUseCaseImpl(get()) }
}
