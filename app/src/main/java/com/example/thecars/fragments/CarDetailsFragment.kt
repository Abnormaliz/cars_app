package com.example.thecars.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.thecars.App
import com.example.thecars.R
import com.example.thecars.adapters.ViewPagerAdapter
import com.example.thecars.classes.Car
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
    private lateinit var actionBar: ActionBar
    private lateinit var database: App
    private lateinit var carDetailsViewModel: CarDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        val selectedCar = arguments?.getParcelable<Car>("selectedCar")!!
        database = (requireContext().applicationContext as App)
        carDetailsViewModel = ViewModelProvider(
            this,
            CarDetailsViewModel.Companion.CarDetailsViewModelFactory(
                database,
                selectedCar
            )
        )
            .get(CarDetailsViewModel::class.java)

        actionBar =
            (requireActivity() as AppCompatActivity).supportActionBar!! // мутка с заголовком
        actionBar.title = "${selectedCar.brand} ${selectedCar.model} ${selectedCar.name}"
        actionBar.setDisplayHomeAsUpEnabled(true)


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
                carDetailsViewModel.addItemToDatabase()
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

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        carDetailsViewModel.isCarExists.observe(viewLifecycleOwner) {
            if (it) {
                editText.visibility = View.VISIBLE
                button.visibility = View.VISIBLE

                button.setOnClickListener {
                    val text = editText.text.toString()
                    carDetailsViewModel.setOrUpdateNote(text)

                    val imm =
                        requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(editText.windowToken, 0)
                    editText.clearFocus()
                }

            } else
                editText.visibility = View.GONE
                button.visibility = View.GONE

            Log.i("values", "${carDetailsViewModel.isCarExists.value}")
        }
        carDetailsViewModel.existingNote.observe(viewLifecycleOwner) {
            if (it != null) {
                editText.setText(it.text)
            } else {
                editText.setText("")
            }
        }


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


