package com.example.thecars

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.thecars.databinding.FragmentDateBinding

/**
 * An example full-screen fragment that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class DateFragment : Fragment() {
    private lateinit var binding: FragmentDateBinding
    lateinit var adapter: DateAdapter


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
        adapter = DateAdapter(getDateList())
        binding.rcViewDate.adapter = adapter

    }

    private fun getDateList(): List<Date> {
        return acura_dates.values
            .flatMap { it }
            .map {
                Date(mdx_images, it)

            }
    }
    companion object {

        @JvmStatic
        fun newInstance() = DateFragment()
    }
}