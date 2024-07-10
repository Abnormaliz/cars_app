package com.example.thecars.app.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageButton
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.thecars.R
import com.example.thecars.app.presentation.adapters.ViewPagerAdapter
import com.example.thecars.app.presentation.models.CarUi
import com.example.thecars.app.presentation.vm.CarDetailsViewModel
import com.example.thecars.databinding.FragmentCarDetailsBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class CarDetailsFragment : Fragment() {

    private lateinit var binding: FragmentCarDetailsBinding
    private lateinit var adapter: ViewPagerAdapter
    private lateinit var tabLayout: TabLayout
    private lateinit var editText: EditText
    private lateinit var button: ImageButton

    private val args: CarDetailsFragmentArgs by navArgs()

    private val selectedCar: CarUi by lazy { args.car }

    private val carDetailsViewModel: CarDetailsViewModel by viewModel {
        parametersOf(selectedCar)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentCarDetailsBinding.inflate(inflater)
        tabLayout = binding.tabLayout
        adapter = ViewPagerAdapter(emptyList())
        binding.viewPager.adapter = adapter
        editText = binding.edTextNotes
        button = binding.button

        lifecycleScope.launch {
            carDetailsViewModel.isCarExists.collect {
                binding.toolbarRemoveButton.isVisible = it
                binding.toolbarAddButton.isVisible = !it
            }
        }


        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarTitle.text = "${selectedCar.brand} ${selectedCar.model} ${selectedCar.car}"
        binding.toolbarAddButton.setOnClickListener {
            carDetailsViewModel.addCar()
        }
        binding.toolbarRemoveButton.setOnClickListener {
            carDetailsViewModel.removeCar()
        }
        binding.toolbarFavButton.setOnClickListener {
            findNavController().navigate(R.id.action_carDetailsFragment_to_favoritesFragment)
        }
        binding.toolbarHomeButton.setOnClickListener {
            findNavController().popBackStack()
        }

        lifecycleScope.launch {
            carDetailsViewModel.isCarExists.collect {
                if (it) {
                    Log.i("values", "${editText.text}")
                    editText.visibility = View.VISIBLE
                   button.visibility = View.VISIBLE
                    button.setOnClickListener {
                        carDetailsViewModel.setOrUpdateNote(editText.text.toString())

                        val imm =
                            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.hideSoftInputFromWindow(editText.windowToken, 0)
                        editText.clearFocus()

                    }

                } else {
                    editText.visibility = View.GONE
                    button.visibility = View.GONE
                    editText.text.clear()
                }
            }

        }
        lifecycleScope.launch {
            carDetailsViewModel.existingNote.collect {
                if (it != null) {
                    editText.setText(it.text)
                } else {
                    editText.setText("")
                }
            }
        }
        lifecycleScope.launch {
            carDetailsViewModel.currentImageList.collect {
                adapter.updateData(it)
                TabLayoutMediator(tabLayout, binding.viewPager) { tab, position ->
                    tab.text = when (position) {
                        0 -> "Front"
                        1 -> "Back"
                        2 -> "Side"
                        else -> "else"
                    }
                }.attach()
            }
        }

    }
}


