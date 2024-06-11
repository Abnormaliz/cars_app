package com.example.thecars.di

import com.example.thecars.domain.models.classes.Brand
import com.example.thecars.domain.models.classes.Car
import com.example.thecars.domain.models.classes.Model
import com.example.thecars.data.MainDb
import com.example.thecars.presentation.vm.BrandsViewModel
import com.example.thecars.presentation.vm.CarDetailsViewModel
import com.example.thecars.presentation.vm.CarViewModel
import com.example.thecars.presentation.vm.FavoritesViewModel
import com.example.thecars.presentation.vm.ModelsViewModel
import com.example.thecars.data.repository.CarsRepositoryImpl
import com.example.thecars.domain.usecases.AddCarToDatabaseUseCase
import com.example.thecars.domain.usecases.AddNoteToDatabaseUseCase
import com.example.thecars.domain.usecases.CheckCarUseCase
import com.example.thecars.domain.usecases.GetNoteByNameUseCase
import com.example.thecars.domain.usecases.ObserveAllCarsUseCase
import com.example.thecars.domain.usecases.RemoveCarFromDatabaseUseCase
import com.example.thecars.domain.usecases.RemoveCarFromFavouritesUseCase
import com.example.thecars.domain.usecases.RemoveNoteFromDatabaseUseCase
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
    factory { ObserveAllCarsUseCase(get()) }
    factory { RemoveCarFromFavouritesUseCase(get()) }
    factory { RemoveCarFromDatabaseUseCase(get()) }
    factory { AddCarToDatabaseUseCase(get()) }
    factory { AddNoteToDatabaseUseCase(get()) }
    factory { RemoveNoteFromDatabaseUseCase(get()) }
    factory { CheckCarUseCase(get()) }
    factory { GetNoteByNameUseCase(get()) }
}
