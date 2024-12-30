package com.example.mobile_programming.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mobile_programming.data.repository.EmployeeRepository

class EmployeeViewModelFactory(
    private val employeeRepository: EmployeeRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EmployeeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EmployeeViewModel(employeeRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
