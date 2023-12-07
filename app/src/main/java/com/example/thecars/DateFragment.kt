package com.example.thecars

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.thecars.adapters.DateAdapter
import com.example.thecars.classes.Date
import com.example.thecars.databinding.FragmentDateBinding
import com.example.thecars.lists.acura_dates
import com.example.thecars.lists.acura_images

/**
 * An example full-screen fragment that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class DateFragment : Fragment() {
    private lateinit var binding: FragmentDateBinding
    private lateinit var adapter: DateAdapter
    private lateinit var currentModel: String
    private lateinit var currentBrand: String
    private lateinit var currentDate: List<String>
    private lateinit var currentImage: List<Int>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDateBinding.inflate(inflater)
        return binding.root

    }

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currentModel = arguments?.getString("model_key")!!
        currentBrand = arguments?.getString("brand_key")!!
        currentDate = acura_dates[currentModel]!!
        currentImage = acura_images[currentModel]!!
        adapter = DateAdapter(
            getDateList(),
            onDateClickListener = {
                requireActivity().supportFragmentManager.beginTransaction()
                .replace(
                    R.id.place_holder,
                    ImagesFragment.newInstance(currentBrand, currentModel, it.title)
                )
                .addToBackStack(null)
                .commit()})
        binding.rcViewDate.adapter = adapter
            Log.d("Mylog", "$currentDate")
    }

    private fun getDateList(): List<Date> {
        val dates = mutableListOf<Date>()
        for (i in currentDate.indices) {
            dates.add(
                Date(
                title = currentDate[i],
                imageId = currentImage[i]
            )
            )
        }
        return dates
    }

    companion object {
        @JvmStatic
            fun newInstance(model: String, brand: String): DateFragment {
                val f = DateFragment()
                val b = Bundle()
                b.putString("model_key", model)
                b.putString("brand_key", brand)
                f.arguments = b
                return f
        }
    }
}