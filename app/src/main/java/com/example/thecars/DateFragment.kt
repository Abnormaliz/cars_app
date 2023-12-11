package com.example.thecars

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.thecars.adapters.DateAdapter
import com.example.thecars.classes.Date
import com.example.thecars.model.DateViewModel
import com.example.thecars.databinding.FragmentDateBinding
import com.example.thecars.interfaces.OnDateClickListener
import com.example.thecars.lists.acura_dates
import com.example.thecars.lists.acura_images

/**
 * An example full-screen fragment that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class DateFragment : Fragment(), OnDateClickListener {
    private lateinit var binding: FragmentDateBinding
    private lateinit var adapter: DateAdapter
    private lateinit var currentModel: String
    private lateinit var currentBrand: String
    private lateinit var currentDate: List<String>
    private lateinit var currentImage: List<Int>
    private val dateViewModel: DateViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDateBinding.inflate(inflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currentModel = arguments?.getString("model_key")!!
        currentBrand = arguments?.getString("brand_key")!!
        currentDate = acura_dates[currentModel]!!
        currentImage = acura_images[currentModel]!!


        adapter = DateAdapter(
            getDateList(), this)
        binding.rcViewDate.adapter = adapter
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

    override fun onDateClick(date: Date) {
        val bundle = Bundle()
        bundle.putString("brand_key", currentBrand)
        bundle.putString("model_key", currentModel)
        bundle.putString("dateImage_key", date.title)
        findNavController().navigate(R.id.action_dateFragment_to_imagesFragment, bundle)
    }

}