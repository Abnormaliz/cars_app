package com.example.thecars.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.thecars.R
import com.example.thecars.adapters.ModelAdapter
import com.example.thecars.classes.Brand
import com.example.thecars.classes.Model
import com.example.thecars.model.ModelsViewModel
import com.example.thecars.databinding.FragmentModelsBinding
import com.example.thecars.interfaces.OnModelClickListener
import com.google.android.material.snackbar.Snackbar


class ModelsFragment : Fragment(), OnModelClickListener {
    private lateinit var binding: FragmentModelsBinding
    private lateinit var adapter: ModelAdapter
    private lateinit var modelsViewModel: ModelsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        val selectedBrand = arguments?.getParcelable<Brand>("selectedBrand")!!
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        modelsViewModel = ViewModelProvider(
            this,
            ModelsViewModel.Companion.ModelViewModelFactory(selectedBrand))
            .get(ModelsViewModel::class.java)

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
                findNavController().navigate(R.id.action_modelsFragment_to_favoritesFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as AppCompatActivity).supportActionBar?.title = modelsViewModel.brandName
        binding = FragmentModelsBinding.inflate(inflater)
        adapter = ModelAdapter(emptyList(), this)
        binding.rcViewModels.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        modelsViewModel.modelList.observe(viewLifecycleOwner) {
            adapter.updateData(it)
        }
    }


    override fun onModelClick(model: Model) {
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