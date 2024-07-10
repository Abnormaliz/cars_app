package com.example.thecars.app.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.thecars.R
import com.example.thecars.app.presentation.adapters.CarAdapter
import com.example.thecars.app.presentation.interfaces.OnCarClickListener
import com.example.thecars.app.presentation.models.CarUi
import com.example.thecars.app.presentation.models.ModelUi
import com.example.thecars.app.presentation.vm.CarViewModel
import com.example.thecars.data.lists.EMPTY_DATA
import com.example.thecars.databinding.FragmentCarBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CarFragment : Fragment(), OnCarClickListener {
    private lateinit var binding: FragmentCarBinding
    private lateinit var adapter: CarAdapter
    private val args : CarFragmentArgs by navArgs()

    private val selectedModel: ModelUi by lazy { args.model }

    private val carViewModel: CarViewModel by viewModel {
        parametersOf(selectedModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentCarBinding.inflate(inflater)
        adapter = CarAdapter(emptyList(), this)
        binding.rcViewCar.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarHomeButton.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.toolbarFavButton.setOnClickListener {
            findNavController().navigate(R.id.action_carFragment_to_favoritesFragment)
        }
        binding.toolbarTitle.text = carViewModel.modelName

        lifecycleScope.launch {
            carViewModel.currentCars.collect {
                adapter.updateData(it)
            }
        }
    }

    override fun onCarClick(car: CarUi) {
        if (car.frontPhoto == 0) {
            Snackbar.make(binding.root, EMPTY_DATA, Snackbar.LENGTH_SHORT).show()
        } else {
            val action = CarFragmentDirections.actionCarFragmentToCarDetailsFragment(car)
            findNavController().navigate(action)
        }
    }
}
