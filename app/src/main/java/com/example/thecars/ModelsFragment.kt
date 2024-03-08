package com.example.thecars

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.thecars.adapters.ModelAdapter
import com.example.thecars.model.ModelsViewModel
import com.example.thecars.databinding.FragmentModelsBinding
import com.example.thecars.interfaces.OnModelClickListener
import com.example.thecars.lists.brandToModels
import com.example.thecars.lists.carsModelToImages
import com.google.android.material.snackbar.Snackbar


class ModelsFragment : Fragment(), OnModelClickListener {
    private lateinit var binding: FragmentModelsBinding
    private lateinit var adapter: ModelAdapter
    private val modelsViewModel: ModelsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentModelsBinding.inflate(inflater)

        arguments?.getString("brand_key")?.let { modelsViewModel.setModelList(it) }

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


    override fun onModelClick(model: String) {
        val modelsMap = carsModelToImages[modelsViewModel.brand.value]
        if (modelsMap!!.contains(model)) {
        val bundle = Bundle()
        bundle.putString("model_key", model)
        bundle.putString("brand_key", modelsViewModel.brand.value)
        findNavController().navigate(R.id.action_modelsFragment_to_dateFragment, bundle)
    } else {
        Snackbar.make(binding.root, "No data at the moment", Snackbar.LENGTH_SHORT).show()
    }
    }
}