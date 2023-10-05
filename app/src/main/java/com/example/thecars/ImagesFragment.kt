package com.example.thecars

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.thecars.databinding.FragmentImagesBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.gtappdevelopers.kotlingfgproject.ViewPagerAdapter

private lateinit var binding: FragmentImagesBinding
class ImagesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentImagesBinding.inflate(inflater)
        return binding.root



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tabLayout: TabLayout = binding.tabLayout
        val images = mdx_images
        val adapter = ViewPagerAdapter(images)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, binding.viewPager) {
                tab, position -> tab.text =
            when (position) {
                0 -> "Front"
                1 -> "Back"
                2 -> "Side"
                else -> "else"
            }
        }.attach()

    }

    companion object {
        @JvmStatic
        fun newInstance() = ImagesFragment()

    }
}