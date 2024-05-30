package com.example.thecars.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.thecars.R
import com.example.thecars.adapters.CarAdapter
import com.example.thecars.classes.Car
import com.example.thecars.classes.Model
import com.example.thecars.databinding.FragmentCarBinding
import com.example.thecars.vm.CarViewModel
import com.example.thecars.interfaces.OnCarClickListener
import com.example.thecars.lists.EMPTY_DATA
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CarFragment : Fragment(), OnCarClickListener {
    private lateinit var binding: FragmentCarBinding
    private lateinit var adapter: CarAdapter
    private lateinit var currentBrand: CharSequence
    private lateinit var actionBar: ActionBar
    private var isTitleSet = false
    var savedActionBar: String? = null

    private val selectedModel: Model by lazy { arguments?.getParcelable<Model>("selectedModel")!! }
    private val carViewModel: CarViewModel by viewModel {
        parametersOf(selectedModel)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onResume() {
        super.onResume()
        if (!isTitleSet) {
            actionBar = (requireActivity() as AppCompatActivity).supportActionBar!!
            currentBrand = actionBar.title!!
            actionBar.title = "$currentBrand ${carViewModel.modelName}"
            savedActionBar = actionBar.title as String
            isTitleSet = true
        } else actionBar.title = savedActionBar
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.actionmenu, menu)
        menu.findItem(R.id.remove).isVisible = false
        menu.findItem(R.id.add).isVisible = false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                findNavController().popBackStack()
            }

            R.id.fav -> {
                findNavController().navigate(R.id.action_carFragment_to_favoritesFragment)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCarBinding.inflate(inflater)
        adapter = CarAdapter(emptyList(), this)
        binding.rcViewCar.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            carViewModel.currentCars.collect {
                adapter.updateData(it)
            }
        }
    }

    override fun onCarClick(car: Car) {
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