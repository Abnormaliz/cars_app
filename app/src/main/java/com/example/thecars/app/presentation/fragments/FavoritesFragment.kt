package com.example.thecars.app.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.thecars.R
import com.example.thecars.app.presentation.adapters.FavoritesAdapter
import com.example.thecars.app.presentation.interfaces.OnItemClickListener
import com.example.thecars.app.presentation.models.CarUi
import com.example.thecars.app.presentation.models.toCarUi
import com.example.thecars.app.presentation.vm.FavoritesViewModel
import com.example.thecars.databinding.FragmentFavoritesBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment : Fragment(), OnItemClickListener {
    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var adapter: FavoritesAdapter
    private val favoritesViewModel: FavoritesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater)
        adapter = FavoritesAdapter(emptyList(), this)
        binding.rcViewFavorites.adapter = adapter
        adapter.longClickFlag.observe(viewLifecycleOwner) {
            binding.toolbarRemoveButton.isVisible = it
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarHomeButton.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.toolbarRemoveButton.setOnClickListener {
            favoritesViewModel.removeCars(adapter.selectedCars)
            adapter.longClickFlag.value = false // TODO понять, как это убрать
        }
        binding.toolbarTitle.text = "Избранное"

        lifecycleScope.launch {
            favoritesViewModel.cars.collectLatest { cars ->
                adapter.updateData(cars.map { it.toCarUi() })
                binding.toolbarRemoveButton.isVisible = false
            }
        }

    }

    override fun onItemClick(car: CarUi) {
        if (!adapter.longClickFlag.value!!) {
            val action = FavoritesFragmentDirections.actionFavoritesFragmentToCarDetailsFragment(car)
            findNavController().navigate(action)
        }
    }
}
