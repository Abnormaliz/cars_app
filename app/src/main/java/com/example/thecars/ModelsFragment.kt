package com.example.thecars

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.thecars.adapters.ModelAdapter
import com.example.thecars.classes.Car
import com.example.thecars.classes.Model
import com.example.thecars.data.CarViewModel
import com.example.thecars.databinding.FragmentModelsBinding
import com.example.thecars.interfaces.OnCarClickListener
import com.example.thecars.interfaces.OnModelClickListener
import com.example.thecars.lists.brandToModels


class ModelsFragment : Fragment(), OnModelClickListener {
    private lateinit var binding: FragmentModelsBinding
    private lateinit var adapter: ModelAdapter
    private lateinit var currentBrand: String
    private val carViewModel: CarViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentModelsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currentBrand = arguments?.getString("brand_key")!!
        adapter = ModelAdapter(getModelList(),this)
        binding.rcViewModels.adapter = adapter



    }

    private fun getModelList(): List<String> {
        val modelArray = brandToModels[currentBrand]
        return resources.getStringArray(modelArray!!).toList()
    }


    override fun onModelClick(model: List<String>) {
        val bundle = Bundle()
        bundle.putString("brand_key", currentBrand)
        bundle.putString("model_key", )
        findNavController().navigate(R.id.action_carsFragment_to_modelsFragment, bundle)
    }
}