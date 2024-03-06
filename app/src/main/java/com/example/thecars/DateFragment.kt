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
import com.example.thecars.model.DateViewModel
import com.example.thecars.databinding.FragmentDateBinding
import com.example.thecars.interfaces.OnDateClickListener
import com.example.thecars.lists.acura_dates
import com.example.thecars.lists.acura_images
import com.google.android.material.snackbar.Snackbar

/**
 * An example full-screen fragment that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
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

        arguments?.getString("model_key")?.let { dateViewModel.setCurrentDate(it) }

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
        if (date.title == "There are no info") {
            Snackbar.make(binding.root, "No data at the moment", Snackbar.LENGTH_SHORT).show()
        } else {
            val bundle = Bundle()
            bundle.putString("dateImage_key", date.title)
            findNavController().navigate(R.id.action_dateFragment_to_imagesFragment, bundle)
        }
    }

}