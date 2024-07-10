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
    private val args: ModelsFragmentArgs by navArgs()

    private val selectedBrand: BrandUi by lazy { args.brand }

    private val modelsViewModel: ModelsViewModel by viewModel {
        parametersOf(selectedBrand)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentModelsBinding.inflate(inflater)
        adapter = ModelAdapter(emptyList(), this)
        binding.rcViewModels.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarHomeButton.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.toolbarFavButton.setOnClickListener {
            findNavController().navigate(R.id.action_modelsFragment_to_favoritesFragment)
        }

        binding.toolbarTitle.text = modelsViewModel.brandName

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
            val action = ModelsFragmentDirections.actionModelsFragmentToCarDetailsFragment(model)
            findNavController().navigate(action)
        }
    }
}