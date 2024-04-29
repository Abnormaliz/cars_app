package com.example.thecars.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.thecars.R
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
    private lateinit var selectedModel: Model
    private lateinit var currentBrand: CharSequence
    private lateinit var actionBar: ActionBar
    private val dateViewModel: DateViewModel by viewModels()
    private var isTitleSet = false
    var savedActionBar: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        selectedModel = arguments?.getParcelable("selectedModel")!!
    }
    override fun onResume() {
        super.onResume()
        if (!isTitleSet) {
            actionBar = (requireActivity() as AppCompatActivity).supportActionBar!!
            currentBrand = actionBar.title!!
            actionBar.title = "$currentBrand ${selectedModel.name}"
            savedActionBar = actionBar.title as String
            isTitleSet = true
        } else actionBar.title = savedActionBar
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
                findNavController().navigate(R.id.action_dateFragment_to_favoritesFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDateBinding.inflate(inflater)
        selectedModel.let { dateViewModel.setCurrentDate(it) }
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