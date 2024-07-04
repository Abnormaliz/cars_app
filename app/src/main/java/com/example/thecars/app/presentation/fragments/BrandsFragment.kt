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
    private lateinit var toolBar: Toolbar
    private val brandsViewModel: BrandsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentBrandsBinding.inflate(inflater)
        adapter = BrandsAdapter(
            emptyList(), this
        )
        binding.rcViewBrands.adapter = adapter
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
                    R.id.fav -> {
                        findNavController().navigate(R.id.action_brandsFragment_to_favoritesFragment)
                        true
                    }

                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        (activity as AppCompatActivity).setSupportActionBar(toolBar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Марка автомобиля"


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
            val bundle = Bundle().apply {
                putParcelable("selectedBrand", brand)
            }
            findNavController().navigate(R.id.action_brandsFragment_to_modelsFragment, bundle)
        }
    }
}
