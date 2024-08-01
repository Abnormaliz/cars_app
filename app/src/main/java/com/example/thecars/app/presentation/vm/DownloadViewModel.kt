package com.example.thecars.app.presentation.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thecars.data.classes.CutCar
import com.example.thecars.data.classes.RemoteCar
import com.example.thecars.data.classes.toCutCar
import com.example.thecars.domain.usecaseImpl.GetCarFromApiUseCaseImpl
import kotlinx.coroutines.launch

class DownloadViewModel(private val getCarFromApiUseCase: GetCarFromApiUseCaseImpl) : ViewModel() {

    private val _cutCars = MutableLiveData<List<CutCar>>()
    val cutCars: LiveData<List<CutCar>> get() = _cutCars

    fun fetchCarData(
        limit: String,
        page: String,
        type: String?,
        model: String?,
        make: String?
    ) {
        viewModelScope.launch {
            try {
                val cars = getCarFromApiUseCase.getCar(
                    limit,
                    page,
                    type,
                    model,
                    make
                ).map { it.toCutCar() }
                _cutCars.postValue(cars)
            } catch (e: Exception) {
                Log.e("DownloadViewModel", "Error fetching car data", e)
            }

        }
    }

}