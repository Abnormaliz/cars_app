package com.example.thecars

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.thecars.model.CarViewModel
import com.example.thecars.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val carViewModel: CarViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        /*supportFragmentManager.beginTransaction().replace(
            R.id.place_holder,
            CarsFragment.newInstance()).addToBackStack(null).commit()*/

    }

}