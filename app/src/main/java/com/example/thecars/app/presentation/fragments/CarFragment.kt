package com.example.thecars.app.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
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
    private lateinit var toolBar: Toolbar

    private val selectedModel: ModelUi by lazy { arguments?.getParcelable("selectedModel")!! }
    private val carViewModel: CarViewModel by viewModel {
        parametersOf(selectedModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentCarBinding.inflate(inflater)
        adapter = CarAdapter(emptyList(), this)
        binding.rcViewCar.adapter = adapter
        toolBar = binding.toolbar
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.fragmentmenu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    android.R.id.home -> {
                        findNavController().popBackStack()
                    }

                    R.id.fav -> {
                        findNavController().navigate(R.id.action_carFragment_to_favoritesFragment)
                        true
                    }

                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        (activity as AppCompatActivity).setSupportActionBar(toolBar)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = carViewModel.modelName

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
            val bundle = Bundle().apply {
                putParcelable("selectedCar", car)
            }
            findNavController().navigate(R.id.action_carFragment_to_carDetailsFragment, bundle)
        }
    }
}
