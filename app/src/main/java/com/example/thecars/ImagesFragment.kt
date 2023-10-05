package com.example.thecars

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.thecars.databinding.FragmentImagesBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.gtappdevelopers.kotlingfgproject.ViewPagerAdapter

private lateinit var binding: FragmentImagesBinding
private lateinit var currentBrand: String
private lateinit var currentDate: String
private lateinit var currentModel: String
class ImagesFragment : Fragment() {
    private fun getImageList(): List<Int> {
        return modelToImages[currentModel]!!

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentImagesBinding.inflate(inflater)
        return binding.root



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentBrand = arguments?.getString("brand_key")!!
        currentModel = arguments?.getString("model_key")!!
        currentDate = arguments?.getString("date_key")!!
        val tabLayout: TabLayout = binding.tabLayout
        val images = getImageList()
        val adapter = ViewPagerAdapter(images)
        binding.viewPager.adapter = adapter




        Log.d("Mylog", "model $currentModel")
        Log.d("Mylog", "date $currentDate")
        Log.d("Mylog", "image ${getImageList()}")
        Log.d("Mylog", "brand $currentBrand")


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
        fun newInstance(brand: String, model: String, date: String): ImagesFragment {
            val f = ImagesFragment()
            val b = Bundle()
            b.putString("model_key", model)
            b.putString("brand_key", brand)
            b.putString("date_key", date)
            f.arguments = b
            return f
        }

    }
}