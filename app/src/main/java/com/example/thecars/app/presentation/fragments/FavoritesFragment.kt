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
    private lateinit var toolBar: Toolbar
    private val favoritesViewModel: FavoritesViewModel by viewModel()
    private var removeButton: MenuItem? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater)
        adapter = FavoritesAdapter(emptyList(), this)
        binding.rcViewFavorites.adapter = adapter
        toolBar = binding.toolbar
        adapter.longClickFlag.observe(viewLifecycleOwner) {
            removeButton?.isVisible = it
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.favtoolbar, menu)
                removeButton = menu.findItem(R.id.remove)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    android.R.id.home -> {
                        findNavController().popBackStack()
                    }

                    R.id.remove -> {
                        favoritesViewModel.removeCars(adapter.selectedCars)
                        adapter.longClickFlag.value = false // TODO понять, как это убрать
                        true
                    }

                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        (activity as AppCompatActivity).setSupportActionBar(toolBar)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Избранное"

        lifecycleScope.launch {
            favoritesViewModel.cars.collectLatest { cars ->
                adapter.updateData(cars.map { it.toCarUi() })
                removeButton?.isVisible = false
            }
        }

    }

    override fun onItemClick(car: CarUi) {
        if (!adapter.longClickFlag.value!!) {
            val bundle = Bundle().apply {
                putParcelable("selectedCar", car)
            }
            findNavController().navigate(
                R.id.action_favoritesFragment_to_carDetailsFragment, bundle
            )
        }
    }
}
