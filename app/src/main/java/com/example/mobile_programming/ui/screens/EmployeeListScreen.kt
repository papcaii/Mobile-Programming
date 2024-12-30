package com.example.mobile_programming.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.padding
import com.example.mobile_programming.data.local.Employee
import com.example.mobile_programming.ui.EmployeeViewModel
import androidx.compose.foundation.layout.PaddingValues

import android.net.ConnectivityManager
import android.content.Context
import android.net.NetworkCapabilities
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.launch

@Composable
fun EmployeeListScreen(
    viewModel: EmployeeViewModel,
    paddingValues: PaddingValues,
    context: Context
) {


    // Observe employees and loading state
    val employees by viewModel.employees.observeAsState(emptyList())
    val isLoading by viewModel.loading.observeAsState(false)

    // Get a CoroutineScope tied to this composable's lifecycle
    val coroutineScope = rememberCoroutineScope()

    var showOnline by remember { mutableStateOf(true) } // Start with "Online" initially

    // Check for network availability
    val isOnline = remember { isNetworkAvailable(context) }

    // Main layout for the screen
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxHeight() // Make the Column take up full height
    ) {

        // Heading and Online/Offline Status
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Employee List",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = if (showOnline) "Online" else "Offline",
                color = if (showOnline) Color.Green else Color.Red,
                style = MaterialTheme.typography.bodyLarge
            )
        }

        // Loading Indicator
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else {
            // Display employees or a message if no employees exist
            if (employees.isEmpty()) {
                Text(
                    text = "No employees found",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            } else {
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(employees) { employee ->
                        EmployeeRow(employee = employee)
                    }
                }
            }
        }

        // Spacer to push the button to the bottom
        Spacer(modifier = Modifier.weight(1f))

        // Button to trigger refresh
        Button(
            onClick = {
                coroutineScope.launch {
                    showOnline = viewModel.refreshEmployees()  // Calls suspend function
                }
            },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Refresh Employees")
        }
    }
}

@Composable
fun EmployeeRow(employee: Employee) {
    // Display employee details with padding and styling
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = employee.name,
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = "Position: ${employee.position}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

// Function to check network connectivity
fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkCapabilities = connectivityManager.activeNetwork?.let {
        connectivityManager.getNetworkCapabilities(it)
    }
    return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
}


