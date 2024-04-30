package com.example.thecars.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.thecars.App
import com.example.thecars.R
import com.example.thecars.adapters.ViewPagerAdapter
import com.example.thecars.classes.Car
import com.example.thecars.data.CarEntity
import com.example.thecars.data.NotesEntity
import com.example.thecars.databinding.FragmentCarDetailsBinding
import com.example.thecars.model.CarDetailsViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.launch


class CarDetailsFragment : Fragment() {

    private lateinit var binding: FragmentCarDetailsBinding
    private lateinit var adapter: ViewPagerAdapter
    private lateinit var tabLayout: TabLayout
    private lateinit var editText: EditText
    private lateinit var button: ImageButton
    private lateinit var selectedCar: Car
    private lateinit var actionBar: ActionBar
    private val carDetailsViewModel: CarDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        selectedCar = arguments?.getParcelable("selectedCar")!!
        actionBar = (requireActivity() as AppCompatActivity).supportActionBar!!
        actionBar.title = "${selectedCar.brand} ${selectedCar.model} ${selectedCar.name}"
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.actionmenu, menu)
        menu.findItem(R.id.remove).isVisible = false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                findNavController().popBackStack()
            }

            R.id.fav -> {
                findNavController().navigate(R.id.action_carDetailsFragment_to_favoritesFragment)
                true
            }

            R.id.add -> {
                val newItem = CarEntity(
                    selectedCar.brand,
                    selectedCar.model,
                    selectedCar.name,
                    selectedCar.previewPhoto
                )
                val appContext = requireContext().applicationContext as App
                val db = appContext.database
                lifecycleScope.launch {
                    val existingItem = db.dao.getItemByName(newItem.carName)
                    if (existingItem == null) {
                        db.dao.insertItem(newItem)
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Автомобиль с именем ${newItem.carName} уже добавлен в Избранное",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCarDetailsBinding.inflate(inflater)
        tabLayout = binding.tabLayout
        adapter = ViewPagerAdapter(emptyList())
        binding.viewPager.adapter = adapter
        editText = binding.edTextNotes
        button = binding.button1
        val appContext = requireContext().applicationContext as App
        val db = appContext.database

        if (selectedCar.isFavorite) {
            editText.visibility = View.VISIBLE
            button.visibility = View.VISIBLE

            var existingNote: NotesEntity? = null
            lifecycleScope.launch {

                existingNote =
                    db.dao.getNoteByName(selectedCar.name)
                if (existingNote != null) {
                    editText.setText(existingNote!!.text)
                }

                val currentNote =
                    db.dao.getNoteByName(selectedCar.name)?.text
                if (currentNote != null)
                    editText.setText(currentNote)
            }

            button.setOnClickListener {
                val text = editText.text.toString()
                if (text.isNotEmpty()) {
                    val newNote = if (existingNote != null) {
                        existingNote!!.copy(text = text)
                    } else {
                        NotesEntity(
                            text,
                            selectedCar.name
                        )
                    }

                    lifecycleScope.launch {
                        db.dao.insertNoteItem(newNote)
                        existingNote = newNote
                    }
                } else {
                    lifecycleScope.launch {
                        if (existingNote != null)
                            db.dao.deleteNoteItem(existingNote!!)
                    }
                }
                val imm =
                    requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(editText.windowToken, 0)
                editText.clearFocus()
            }

        }

        selectedCar.let { carDetailsViewModel.setCurrentCar(it) }



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        carDetailsViewModel.currentImageList.observe(viewLifecycleOwner) {
            adapter.updateData(it)

            TabLayoutMediator(tabLayout, binding.viewPager) { tab, position ->
                tab.text =
                    when (position) {
                        0 -> "Front"
                        1 -> "Back"
                        2 -> "Side"
                        else -> "else"
                    }
            }.attach()
        }

    }
}


