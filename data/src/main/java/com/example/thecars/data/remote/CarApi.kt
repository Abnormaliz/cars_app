package com.example.thecars.data.remote

import com.example.thecars.data.classes.RemoteCar
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface CarApi {
    @Headers("x-rapidapi-key: df806e2ec1msh4f21bda8b2d5080p19684ajsn451cd7acd75c")
    @GET("cars")
    suspend fun getCar(
        @Query("limit") limit: String,
        @Query("page") page: String,
        @Query("type") type : String? = null,
        @Query("model") model : String? = null,
        @Query("make") make : String? = null
    ): List<RemoteCar>
}