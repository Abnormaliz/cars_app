package com.example.thecars

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
import androidx.navigation.fragment.findNavController
import com.example.thecars.adapters.ModelAdapter
import com.example.thecars.classes.Car
import com.example.thecars.classes.Model
import com.example.thecars.model.ModelsViewModel
import com.example.thecars.databinding.FragmentModelsBinding
import com.example.thecars.interfaces.OnModelClickListener
import com.google.android.material.snackbar.Snackbar


class ModelsFragment : Fragment(), OnModelClickListener {
    private lateinit var binding: FragmentModelsBinding
    private lateinit var adapter: ModelAdapter
    private val modelsViewModel: ModelsViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.actionmenu, menu)
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
    ): View? {
        binding = FragmentModelsBinding.inflate(inflater)

        arguments?.getParcelable<Car>("selectedCar")?.let { modelsViewModel.setModelList(it) }

        adapter = ModelAdapter(emptyList(), this)
        binding.rcViewModels.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = modelsViewModel.selectedCar?.title


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
            findNavController().navigate(R.id.action_modelsFragment_to_dateFragment, bundle)
        }
    }
}