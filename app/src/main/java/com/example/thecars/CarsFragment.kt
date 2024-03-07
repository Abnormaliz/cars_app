package com.example.thecars

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.thecars.adapters.CarAdapter
import com.example.thecars.classes.Car
import com.example.thecars.model.CarViewModel
import com.example.thecars.databinding.FragmentCarsBinding
import com.example.thecars.interfaces.OnCarClickListener
import com.example.thecars.lists.brandToModels
import com.google.android.material.snackbar.Snackbar


class  CarsFragment : Fragment(), OnCarClickListener {
    private lateinit var binding: FragmentCarsBinding
    private lateinit var adapter: CarAdapter
    private val carViewModel: CarViewModel by viewModels()

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

        carViewModel.carList.observe(viewLifecycleOwner) {
            adapter.updateData(it)
        }
    }
        override fun onCarClick(car: Car) {
            if (brandToModels.containsKey(car.title)) {
                val bundle = Bundle()
                bundle.putString("brand_key", car.title)
                findNavController().navigate(R.id.action_carsFragment_to_modelsFragment, bundle)
            } else {
                Snackbar.make(binding.root, "No data at the moment", Snackbar.LENGTH_SHORT).show()
            }
    }
}
