package com.example.thecars

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.thecars.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction().replace(R.id.place_holder, CarsFragment.newInstance()).addToBackStack(null).commit()

    }

}