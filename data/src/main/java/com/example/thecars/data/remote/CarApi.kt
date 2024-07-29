package com.example.thecars.data.remote

import com.example.thecars.data.classes.RemoteCar
import retrofit2.http.GET

interface CarApi {
    @GET("cars?model=m5&limit=2&page=0")
    fun getCar(): RemoteCar

}