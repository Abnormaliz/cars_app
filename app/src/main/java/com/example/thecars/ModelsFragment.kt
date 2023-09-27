package com.example.thecars

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.thecars.databinding.FragmentModelsBinding


class ModelsFragment : Fragment() {
    private lateinit var binding: FragmentModelsBinding
    lateinit var adapter: ModelAdapter
    lateinit var currentBrand: String

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
        adapter = ModelAdapter(getModelList())
        binding.rcViewModels.adapter = adapter

        Log.d("Mylog", "brand $currentBrand")



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