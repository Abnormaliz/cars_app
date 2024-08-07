package com.example.thecars.app.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
import com.example.thecars.data.remote.apiBrands
import com.example.thecars.data.remote.apiLimits
import com.example.thecars.data.remote.apiTypes
import com.example.thecars.data.remote.apiYears
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
        var year by remember { mutableStateOf("") }

        Column(modifier = Modifier.padding(16.dp)) {
            DropDownMenu(
                items = apiLimits,
                selectedItem = limit,
                onItemSelected = { selectedItem -> limit = selectedItem},
                text = "Amount to show")
            TextField(
                value = page,
                onValueChange = { page = it },
                label = { Text("Page, write 1-191") },
                modifier = Modifier.fillMaxWidth()
            )
            DropDownMenu(
                items = apiBrands,
                selectedItem = make,
                onItemSelected = { selectedItem -> make = selectedItem},
                text = "Brand of the car")
            TextField(
                value = model,
                onValueChange = { model = it },
                label = { Text("Model") },
                modifier = Modifier.fillMaxWidth()
            )
            DropDownMenu(
                items = apiTypes,
                selectedItem = type,
                onItemSelected = { selectedItem -> type = selectedItem},
                text = "Type of the car")
            DropDownMenu(
                items = apiYears,
                selectedItem = year,
                onItemSelected = { selectedItem -> year = selectedItem},
                text = "Release year")

            Button(
                onClick = {
                    viewModel.fetchCarData(limit, page, type, model, make, year)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownMenu(items: List<String>, selectedItem: String, onItemSelected: (String) -> Unit, text: String) {

    var isExpanded by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
        ) {
        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = !isExpanded })
        {
            TextField(
                label = {Text(text)},
                modifier = Modifier.menuAnchor(),
                value = selectedItem,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            })
            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false }) {
                items.forEach {item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            onItemSelected(item)
                            isExpanded = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding)
                }
            }
        }
    }
}


