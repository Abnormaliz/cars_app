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
import com.example.thecars.app.presentation.adapters.ModelAdapter
import com.example.thecars.app.presentation.interfaces.OnModelClickListener
import com.example.thecars.app.presentation.models.BrandUi
import com.example.thecars.app.presentation.models.ModelUi
import com.example.thecars.app.presentation.vm.ModelsViewModel
import com.example.thecars.databinding.FragmentModelsBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class ModelsFragment : Fragment(), OnModelClickListener {
    private lateinit var binding: FragmentModelsBinding
    private lateinit var adapter: ModelAdapter
    private lateinit var toolBar: Toolbar

    private val selectedBrand: BrandUi by lazy { arguments?.getParcelable("selectedBrand")!! }

    private val modelsViewModel: ModelsViewModel by viewModel {
        parametersOf(selectedBrand)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentModelsBinding.inflate(inflater)
        adapter = ModelAdapter(emptyList(), this)
        binding.rcViewModels.adapter = adapter
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
                        findNavController().navigate(R.id.action_modelsFragment_to_favoritesFragment)
                        true
                    }

                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        (activity as AppCompatActivity).setSupportActionBar(toolBar)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = modelsViewModel.brandName

        lifecycleScope.launch {
            modelsViewModel.modelList.collect {
                adapter.updateData(it)
            }
        }
    }


    override fun onModelClick(model: ModelUi) {
        if (model.list.isEmpty()) {
            Snackbar.make(binding.root, "No data at the moment", Snackbar.LENGTH_SHORT).show()
        } else {
            val bundle = Bundle().apply {
                putParcelable("selectedModel", model)
            }
            findNavController().navigate(R.id.action_modelsFragment_to_carDetailsFragment, bundle)
        }
    }
}