package com.example.thecars.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.thecars.R
import com.example.thecars.adapters.CarAdapter
import com.example.thecars.classes.Car
import com.example.thecars.model.CarViewModel
import com.example.thecars.databinding.FragmentCarsBinding
import com.example.thecars.interfaces.OnCarClickListener
import com.google.android.material.snackbar.Snackbar


class  CarsFragment : Fragment(), OnCarClickListener {
    private lateinit var binding: FragmentCarsBinding
    private lateinit var adapter: CarAdapter
    private val carViewModel: CarViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onResume() {
        super.onResume()
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.actionmenu, menu)
        menu.findItem(R.id.remove).isVisible = false
        menu.findItem(R.id.add).isVisible = false
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.fav -> {
                findNavController().navigate(R.id.action_carsFragment_to_favoritesFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
       binding = FragmentCarsBinding.inflate(inflater)
        adapter = CarAdapter(emptyList(), this)
        binding.rcViewCars.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Марка автомобиля"
        carViewModel.carList.observe(viewLifecycleOwner) {
            adapter.updateData(it)
        }
    }
        override fun onCarClick(car: Car) {
            if (car.modelList.isEmpty()) {
                Snackbar.make(binding.root, "No data at the moment", Snackbar.LENGTH_SHORT).show()
            } else {
                val bundle = Bundle().apply {
                    putParcelable("selectedCar", car)
                }
                findNavController().navigate(R.id.action_carsFragment_to_modelsFragment, bundle)
            }
    }
}
