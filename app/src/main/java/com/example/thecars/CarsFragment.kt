package com.example.thecars

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.thecars.adapters.CarAdapter
import com.example.thecars.classes.Car
import com.example.thecars.data.CarViewModel
import com.example.thecars.databinding.FragmentCarsBinding
import com.example.thecars.interfaces.OnCarClickListener


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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val carNames = resources.getStringArray(R.array.car_names)
        val carList = carViewModel.getCarList(carNames)

        adapter = CarAdapter(carList, findNavController()
            .navigate(R.id.action_carsFragment_to_modelsFragment),
            this)
        binding.rcViewCars.adapter = adapter
    }

    override fun onCarClick(car: Car) {
        carViewModel.updateCurrentBrand(car.title)
    }

    companion object {

        @JvmStatic
        fun newInstance() = CarsFragment()
    }



}