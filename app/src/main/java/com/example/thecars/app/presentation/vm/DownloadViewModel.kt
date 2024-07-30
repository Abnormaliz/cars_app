package com.example.thecars.app.presentation.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thecars.data.classes.RemoteCar
import com.example.thecars.domain.usecaseImpl.GetCarFromApiUseCaseImpl
import kotlinx.coroutines.launch

class DownloadViewModel(getCarFromApiUseCase: GetCarFromApiUseCaseImpl): ViewModel() {

    private val _carData = MutableLiveData<List<RemoteCar>>()
    val carData: LiveData<List<RemoteCar>> get() = _carData

    init {
        viewModelScope.launch {
            try {
                val cars = getCarFromApiUseCase.getCar()
                _carData.postValue(cars)
            } catch (e: Exception) {
                Log.e("DownloadViewModel", "Error fetching car data", e)
            }
        }
    }
}