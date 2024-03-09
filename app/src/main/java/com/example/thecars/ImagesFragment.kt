package com.example.thecars

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.thecars.adapters.ViewPagerAdapter
import com.example.thecars.classes.Date
import com.example.thecars.databinding.FragmentImagesBinding
import com.example.thecars.lists.EMPTY_DATA
import com.example.thecars.model.ImagesViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator



class ImagesFragment : Fragment() {

    private lateinit var binding: FragmentImagesBinding
    private lateinit var adapter: ViewPagerAdapter
    private lateinit var tabLayout: TabLayout
    private val imagesViewModel : ImagesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentImagesBinding.inflate(inflater)


        adapter = ViewPagerAdapter(emptyList())
        binding.viewPager.adapter = adapter

        arguments?.getParcelable<Date>("selectedDate")?.let { imagesViewModel.setCurrentDate(it) }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imagesViewModel.currentImageList.observe(viewLifecycleOwner) { imageList ->
            adapter.updateData(imageList)

            if (imageList.isEmpty()) {
                binding.viewPager.visibility = View.GONE
                Snackbar.make(binding.root, EMPTY_DATA, Snackbar.LENGTH_SHORT).show()
            } else {
                tabLayout = binding.tabLayout
                binding.viewPager.visibility = View.VISIBLE
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
}