package com.example.thecars

import android.app.Application
import com.example.thecars.data.MainDb

class App : Application() {
    val database by lazy { MainDb.createDatabase(this) }
}