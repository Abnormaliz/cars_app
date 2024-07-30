package com.example.thecars.app.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.example.thecars.app.presentation.vm.DownloadViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class DownloadFragment : Fragment() {

    private val downloadViewModel: DownloadViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                text(downloadViewModel)
            }
        }
    }
}

@Composable
fun text(viewModel: DownloadViewModel){
    val carData by viewModel.carData.observeAsState()

    carData?.let { cars ->
        cars.forEach {car ->
            Text(text = car.toString())
        }
    } ?: Text(text = "Loading...")
}
