package com.example.thecars

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.thecars.adapters.ModelAdapter
import com.example.thecars.data.CarViewModel
import com.example.thecars.databinding.FragmentModelsBinding
import com.example.thecars.lists.brandToModels


class ModelsFragment : Fragment() {
    private lateinit var binding: FragmentModelsBinding
    private lateinit var adapter: ModelAdapter
    private lateinit var currentBrand: String
    private val carViewModel: CarViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentModelsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        carViewModel.setCurrentBrand()
        carViewModel.get

        currentBrand = arguments?.getString("brand_key")!!
        adapter = ModelAdapter(getModelList(),findNavController()
            .navigate(R.id.action_modelsFragment_to_dateFragment))
        binding.rcViewModels.adapter = adapter



    }

    private fun getModelList(): List<String> {
        val modelArray = brandToModels[currentBrand]
        return resources.getStringArray(modelArray!!).toList()

    }


    companion object {
        @JvmStatic
        fun newInstance(brand: String): ModelsFragment {
            val f = ModelsFragment()
            val b = Bundle()
            b.putString("brand_key", brand)
            f.arguments = b
            return f
        }
    }
}