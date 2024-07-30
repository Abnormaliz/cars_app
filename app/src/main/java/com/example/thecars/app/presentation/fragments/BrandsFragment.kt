package com.example.thecars.app.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.thecars.R
import com.example.thecars.app.presentation.adapters.BrandsAdapter
import com.example.thecars.app.presentation.interfaces.OnBrandClickListener
import com.example.thecars.app.presentation.models.BrandUi
import com.example.thecars.app.presentation.vm.BrandsViewModel
import com.example.thecars.databinding.FragmentBrandsBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class BrandsFragment : Fragment(), OnBrandClickListener {
    private lateinit var binding: FragmentBrandsBinding
    private lateinit var adapter: BrandsAdapter
    private val brandsViewModel: BrandsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentBrandsBinding.inflate(inflater)
        adapter = BrandsAdapter(
            emptyList(), this
        )
        binding.rcViewBrands.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarFavButton.setOnClickListener {
            findNavController().navigate(R.id.action_brandsFragment_to_downloadFragment)
        }
        binding.toolbarTitle.text = "Марка автомобиля"

        lifecycleScope.launch {
            brandsViewModel.carList.collect {
                adapter.updateData(it)
            }
        }

    }

    override fun onBrandClick(brand: BrandUi) {
        if (brand.modelList.isEmpty()) {
            Snackbar.make(binding.root, "No data at the moment", Snackbar.LENGTH_SHORT).show()
        } else {
            val action = BrandsFragmentDirections.actionBrandsFragmentToModelsFragment(brand)
            findNavController().navigate(action)
        }
    }
}
