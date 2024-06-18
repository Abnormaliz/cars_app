package com.example.thecars.di

import com.example.thecars.app.presentation.models.BrandUi
import com.example.thecars.app.presentation.models.CarUi
import com.example.thecars.app.presentation.models.ModelUi
import com.example.thecars.app.presentation.vm.BrandsViewModel
import com.example.thecars.app.presentation.vm.CarDetailsViewModel
import com.example.thecars.app.presentation.vm.CarViewModel
import com.example.thecars.app.presentation.vm.FavoritesViewModel
import com.example.thecars.app.presentation.vm.ModelsViewModel
import com.example.thecars.data.MainDb
import com.example.thecars.data.repositoryImpl.CarsRepositoryImpl
import com.example.thecars.domain.usecaseImpl.AddCarToDatabaseUseCaseImpl
import com.example.thecars.domain.usecaseImpl.AddNoteToDatabaseUseCaseImpl
import com.example.thecars.domain.usecaseImpl.CheckCarUseCaseImpl
import com.example.thecars.domain.usecaseImpl.GetNoteByNameUseCaseImpl
import com.example.thecars.domain.usecaseImpl.ObserveAllCarsUseCaseImpl
import com.example.thecars.domain.usecaseImpl.RemoveCarFromDatabaseUseCaseImpl
import com.example.thecars.domain.usecaseImpl.RemoveCarFromFavouritesUseCaseImpl
import com.example.thecars.domain.usecaseImpl.RemoveNoteFromDatabaseUseCaseImpl
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    single<com.example.thecars.data.MainDb> { com.example.thecars.data.MainDb.createDatabase(androidContext()) }
    single { com.example.thecars.data.repositoryImpl.CarsRepositoryImpl(get()) }

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
