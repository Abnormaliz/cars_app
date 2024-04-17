package com.example.thecars.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.thecars.App
import com.example.thecars.R
import com.example.thecars.adapters.ViewPagerAdapter
import com.example.thecars.classes.Date
import com.example.thecars.data.MainDb
import com.example.thecars.data.NameEntity
import com.example.thecars.data.NotesEntity
import com.example.thecars.databinding.FragmentImagesBinding
import com.example.thecars.lists.EMPTY_DATA
import com.example.thecars.model.ImagesViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.launch


class ImagesFragment : Fragment() {

    private lateinit var binding: FragmentImagesBinding
    private lateinit var adapter: ViewPagerAdapter
    private lateinit var tabLayout: TabLayout
    private lateinit var editText: EditText
    private lateinit var button: ImageButton
    private val imagesViewModel: ImagesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)


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
                findNavController().navigate(R.id.action_imagesFragment_to_favoritesFragment)
                true
            }

            R.id.add -> {
                val currentDate = arguments?.getParcelable<Date>("selectedDate")
                val newItem = NameEntity(
                    currentDate!!.brand,
                    currentDate!!.model,
                    currentDate!!.name,
                    currentDate!!.previewPhoto
                )
                val appContext = requireContext().applicationContext as App
                val db = appContext.database
                lifecycleScope.launch {
                    val existingItem = db.dao.getItemByName(newItem.name)
                    if (existingItem == null) {
                        db.dao.insertItem(newItem)
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Автомобиль с именем ${newItem.name} уже добавлен в Избранное",
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
    ): View? {
        binding = FragmentImagesBinding.inflate(inflater)

        tabLayout = binding.tabLayout
        adapter = ViewPagerAdapter(emptyList())
        binding.viewPager.adapter = adapter
        editText = binding.edTextNotes
        button = binding.button1
        val appContext = requireContext().applicationContext as App
        val db = appContext.database

        if (arguments?.getParcelable<Date>("selectedDate")?.isFavorite == 1) {
            editText.visibility = View.VISIBLE
            button.visibility = View.VISIBLE

            var existingNote: NotesEntity? = null
            lifecycleScope.launch {

                existingNote = db.dao.getNoteByName(arguments?.getParcelable<Date>("selectedDate")!!.name)
                if (existingNote != null) {
                    editText.setText(existingNote!!.text)
                }

            val currentNote = db.dao.getNoteByName(arguments?.getParcelable<Date>("selectedDate")!!.name)?.text
                if (currentNote != null)
                editText.setText(currentNote)
            }

            button.setOnClickListener() {
                val text = editText.text.toString()
                if (text.isNotEmpty()) {
                    val newNote = if (existingNote != null) {
                        existingNote!!.copy(text = text)
                    } else {
                        NotesEntity(text, arguments?.getParcelable<Date>("selectedDate")!!.name)
                    }

                    lifecycleScope.launch() {
                        db.dao.insertNoteItem(newNote)
                        existingNote = newNote
                    }
                } else { lifecycleScope.launch() {
                    if (existingNote != null)
                    db.dao.deleteNoteItem(existingNote!!)
                }
                }
                val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(editText.windowToken, 0)
                editText.clearFocus()
            }

        }

        arguments?.getParcelable<Date>("selectedDate")?.let { imagesViewModel.setCurrentDate(it) }



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            val newActionBar = "${imagesViewModel.selectedDate?.brand} ${imagesViewModel.selectedDate?.model} ${imagesViewModel.selectedDate?.name}"
            (requireActivity() as AppCompatActivity).supportActionBar?.title = newActionBar


            imagesViewModel.currentImageList.observe(viewLifecycleOwner) {
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


