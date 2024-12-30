package com.example.mobile_programming

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold

import com.example.mobile_programming.data.local.EmployeeDatabase
import com.example.mobile_programming.data.remote.RetrofitInstance
import com.example.mobile_programming.data.repository.EmployeeRepository
import com.example.mobile_programming.ui.screens.EmployeeListScreen
import com.example.mobile_programming.ui.theme.MobileProgrammingTheme
import com.example.mobile_programming.ui.EmployeeViewModel
import com.example.mobile_programming.ui.EmployeeViewModelFactory

import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider

class MainActivity : ComponentActivity() {

    // Use the viewModels delegate to get the ViewModel
    private val viewModel: EmployeeViewModel by viewModels {
        // Initialize the repository with the actual ApiService and EmployeeDao
        val employeeDao = EmployeeDatabase.getInstance(applicationContext).employeeDao()
        val employeeRepository = EmployeeRepository(
            apiService = RetrofitInstance.api,
            employeeDao = employeeDao
        )
        EmployeeViewModelFactory(employeeRepository) // Use this factory to create the ViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MobileProgrammingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
                    // Pass padding values to the EmployeeListScreen composable
                    EmployeeListScreen(
                        viewModel = viewModel,
                        paddingValues = paddingValues,
                        context = applicationContext
                    )
                }
            }
        }
    }
}

