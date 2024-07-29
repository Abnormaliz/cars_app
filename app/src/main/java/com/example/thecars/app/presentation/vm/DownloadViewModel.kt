package com.example.thecars.app.presentation.vm

import androidx.lifecycle.ViewModel
import com.example.thecars.domain.usecaseImpl.GetCarFromApiUseCaseImpl

class DownloadViewModel(getCarFromApiUseCase: GetCarFromApiUseCaseImpl): ViewModel() {

    val car = getCarFromApiUseCase.getCar()
}