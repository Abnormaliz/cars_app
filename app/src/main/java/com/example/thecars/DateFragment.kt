package com.example.thecars

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.thecars.adapters.DateAdapter
import com.example.thecars.classes.Date
import com.example.thecars.classes.Model
import com.example.thecars.model.DateViewModel
import com.example.thecars.databinding.FragmentDateBinding
import com.example.thecars.interfaces.OnDateClickListener
import com.example.thecars.lists.EMPTY_DATA
import com.google.android.material.snackbar.Snackbar

class DateFragment : Fragment(), OnDateClickListener {
    private lateinit var binding: FragmentDateBinding
    private lateinit var adapter: DateAdapter
    private val dateViewModel: DateViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDateBinding.inflate(inflater)

        arguments?.getParcelable<Model>("selectedModel")?.let { dateViewModel.setCurrentDate(it) }

        adapter = DateAdapter(emptyList(), this)
        binding.rcViewDate.adapter = adapter

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        dateViewModel.currentDate.observe(viewLifecycleOwner) {
            adapter.updateData(it)
        }
    }

    override fun onDateClick(date: Date) {
        if (date.frontPhoto == 0) {
            Snackbar.make(binding.root, EMPTY_DATA, Snackbar.LENGTH_SHORT).show()
        } else {
            val bundle = Bundle().apply {
                putParcelable("selectedDate", date)
            }
            findNavController().navigate(R.id.action_dateFragment_to_imagesFragment, bundle)
        }
    }

}