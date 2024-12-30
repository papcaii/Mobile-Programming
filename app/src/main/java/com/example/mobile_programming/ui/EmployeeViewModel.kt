package com.example.mobile_programming.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mobile_programming.data.local.Employee
import com.example.mobile_programming.data.repository.EmployeeRepository

class EmployeeViewModel(
    private val employeeRepository: EmployeeRepository
) : ViewModel() {

    // LiveData to observe employees in the UI
    val employees: LiveData<List<Employee>> = employeeRepository.employees

    // LiveData to observe loading state
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    // You can create suspend functions if needed for non-live access
    suspend fun getEmployeesSync(): List<Employee> {
        return employeeRepository.getEmployeesSync()
    }

    // Use this method to trigger a refresh from the API and update DB
    suspend fun refreshEmployees(): Boolean {
        _loading.value = true
        var isServerAccessible = false
        try {
            isServerAccessible = employeeRepository.getEmployeesFromAPI()
        } catch (e: Exception) {
            // Handle exception (e.g., show a toast or log the error)
            // Optionally load from the database if there's an error during the network fetch

        }

        if (!isServerAccessible){
            Log.d("EmployeeViewModel", "No access to server, using database")
        }
        _loading.value = false
        return isServerAccessible
    }

    // Helper function to load employees from the local database
    private suspend fun loadEmployeesFromDatabase() {
        // Load employees from the local database (e.g., Room)
        val localEmployees = employeeRepository.getEmployeesSync()
    }

}