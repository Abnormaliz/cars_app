package com.example.thecars

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.thecars.adapters.ViewPagerAdapter
import com.example.thecars.classes.Date
import com.example.thecars.databinding.FragmentImagesBinding
import com.example.thecars.model.ImagesViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator



class ImagesFragment : Fragment() {

    private lateinit var binding: FragmentImagesBinding
    private lateinit var adapter: ViewPagerAdapter
    private lateinit var tabLayout: TabLayout
    private val imagesViewModel : ImagesViewModel by viewModels()
    private var isTitleSet = false
    private var previousTitle: CharSequence? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentImagesBinding.inflate(inflater)

        tabLayout = binding.tabLayout
        adapter = ViewPagerAdapter(emptyList())
        binding.viewPager.adapter = adapter

        arguments?.getParcelable<Date>("selectedDate")?.let { imagesViewModel.setCurrentDate(it) }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        previousTitle = (requireActivity() as AppCompatActivity).supportActionBar?.title
        if (!isTitleSet) {
            val actionBar = (requireActivity() as AppCompatActivity).supportActionBar?.title
            val newActionBar = "$actionBar ${imagesViewModel.selectedDate?.name}"
            (requireActivity() as AppCompatActivity).supportActionBar?.title = newActionBar
            isTitleSet = true
        }

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
    override fun onPause() {
        super.onPause()

        (requireActivity() as AppCompatActivity).supportActionBar?.title = previousTitle
        isTitleSet = false
    }
    }

