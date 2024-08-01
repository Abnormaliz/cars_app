package com.example.thecars.app.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
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
                Column(modifier = Modifier.background(Color.Gray)) {
                    DownloadScreen(viewModel = downloadViewModel)
                }
            }
        }
    }
}

@Composable
fun DownloadScreen(viewModel: DownloadViewModel) {

    Column {
        val cutCar by viewModel.cutCars.observeAsState()

        var limit by remember { mutableStateOf("") }
        var page by remember { mutableStateOf("") }
        var type by remember { mutableStateOf("") }
        var model by remember { mutableStateOf("") }
        var make by remember { mutableStateOf("") }

        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                TextField(
                    value = limit,
                    onValueChange = { limit = it },
                    label = { Text("Limit") },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                )
                Text(
                    text = limit,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                )
            }
            TextField(
                value = page,
                onValueChange = { page = it },
                label = { Text("Page") },
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = type,
                onValueChange = { type = it },
                label = { Text("Type") },
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = model,
                onValueChange = { model = it },
                label = { Text("Model") },
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = make,
                onValueChange = { make = it },
                label = { Text("Make") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    viewModel.fetchCarData(limit, page, type, model, make)
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Fetch Cars")
            }

            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .verticalScroll(scrollState)
                    .fillMaxSize()
            ) {
                cutCar?.let { cars ->
                    cars.forEach { car ->
                        Text(text = car.toString(), modifier = Modifier.padding(top = 8.dp))
                    }
                } ?: Text(text = "Loading...", modifier = Modifier.padding(top = 8.dp))
            }
        }
    }
}
