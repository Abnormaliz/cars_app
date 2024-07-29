package com.example.thecars.di

import com.example.thecars.app.presentation.models.BrandUi
import com.example.thecars.app.presentation.models.CarUi
import com.example.thecars.app.presentation.models.ModelUi
import com.example.thecars.app.presentation.vm.BrandsViewModel
import com.example.thecars.app.presentation.vm.CarDetailsViewModel
import com.example.thecars.app.presentation.vm.CarViewModel
import com.example.thecars.app.presentation.vm.DownloadViewModel
import com.example.thecars.app.presentation.vm.FavoritesViewModel
import com.example.thecars.app.presentation.vm.ModelsViewModel
import com.example.thecars.data.CarDatabase
import com.example.thecars.data.remote.CarApi
import com.example.thecars.data.repositoryImpl.CarsRepositoryImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val appModule = module {
    single { CarDatabase.createDatabase(androidContext()) }
    single { CarsRepositoryImpl(get(), get()) }
    single {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        Retrofit.Builder()
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(httpLoggingInterceptor)
                    .build()
            )
            .baseUrl("https://car-data.p.rapidapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CarApi::class.java)
    }

    viewModel<CarDetailsViewModel> { (selectedCar: CarUi) ->
        CarDetailsViewModel(
            selectedCar,
            get(),
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }
    viewModel<FavoritesViewModel> { FavoritesViewModel(get(), get()) }
    viewModel<BrandsViewModel> { BrandsViewModel() }
    viewModel<ModelsViewModel> { (selectedBrand: BrandUi) -> ModelsViewModel(selectedBrand) }
    viewModel<CarViewModel> { (selectedModel: ModelUi) -> CarViewModel(selectedModel) }
    viewModel<DownloadViewModel> {DownloadViewModel(get())}

    //Usecases
    factory { com.example.thecars.domain.usecaseImpl.ObserveAllCarsUseCaseImpl(get()) }
    factory { com.example.thecars.domain.usecaseImpl.RemoveCarFromFavouritesUseCaseImpl(get()) }
    factory { com.example.thecars.domain.usecaseImpl.RemoveCarFromDatabaseUseCaseImpl(get()) }
    factory { com.example.thecars.domain.usecaseImpl.AddCarToDatabaseUseCaseImpl(get()) }
    factory { com.example.thecars.domain.usecaseImpl.AddNoteToDatabaseUseCaseImpl(get()) }
    factory { com.example.thecars.domain.usecaseImpl.RemoveNoteFromDatabaseUseCaseImpl(get()) }
    factory { com.example.thecars.domain.usecaseImpl.CheckCarUseCaseImpl(get()) }
    factory { com.example.thecars.domain.usecaseImpl.GetNoteByNameUseCaseImpl(get()) }
    factory { com.example.thecars.domain.usecaseImpl.GetCarFromApiUseCaseImpl(get()) }
}
