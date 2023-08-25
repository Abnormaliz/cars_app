package com.example.thecars

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thecars.databinding.FragmentModelsBinding


class ModelsFragment : Fragment() {
    private lateinit var binding: FragmentModelsBinding
    lateinit var adapter: ModelAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentModelsBinding.inflate(inflater)
        return binding.root
    }
        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            adapter = ModelAdapter(getModelList())
            binding.rcViewModels.adapter = adapter

    }
    private fun getModelList(): List<String> {
        return this.resources.getStringArray(R.array.acura_models).toList()
    }


    companion object {
        @JvmStatic
        fun newInstance() = ModelsFragment()
    }
}