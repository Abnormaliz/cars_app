package com.example.thecars.app

import android.app.Application
import com.example.thecars.di.appModule
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {

//    private lateinit var logoApi: LogoApi
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(appModule)
        }
    }

//    private fun configureRetrofit() {
//        val httpLoggingInterceptor = HttpLoggingInterceptor()
//        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
//
//        val okHttpClient = OkHttpClient.Builder()
//            .addInterceptor(httpLoggingInterceptor)
//            .build()
//
//        val retrofit = Retrofit.Builder()
//            .baseUrl("'https://dummyjson.com")
//            .client(okHttpClient)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
////        logoApi = retrofit.create(LogoApi::class.java)
//    }
}
