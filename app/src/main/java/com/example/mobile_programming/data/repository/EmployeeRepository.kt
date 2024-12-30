package com.example.mobile_programming.data.repository

import android.util.Log
import com.example.mobile_programming.data.local.EmployeeDao
import com.example.mobile_programming.data.remote.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class EmployeeRepository(
    private val apiService: ApiService,
    private val employeeDao: EmployeeDao
) {

    // Asynchronous live data for observing employees
    val employees = employeeDao.getAllEmployees()

    // Synchronous access for non-live data
    suspend fun getEmployeesSync() = employeeDao.getAllEmployeesSync()

    // Refresh employees data by fetching from API and updating the local database
    suspend fun getEmployeesFromAPI(): Boolean {
        var success = false
        return withContext(Dispatchers.IO) {
            try {
                // Fetch from API
                val fetchedEmployees = apiService.getEmployees()
                Log.d("EmployeeRepository", "Fetched employees: $fetchedEmployees")

                // Clear existing employees in DB and insert the new ones
                employeeDao.clearEmployees()
                employeeDao.insertEmployees(fetchedEmployees)

                // Debugging
                Log.d("EmployeeRepository", "Employees added to DB")
                success = true
            } catch (e: HttpException) {
                Log.e("EmployeeRepository", "API error: ${e.message()}", e)
                success = false //
            } catch (e: IOException) {
                Log.e("EmployeeRepository", "Network error: ${e.message}", e)
                success = false
            } catch (e: Exception) {
                Log.e("EmployeeRepository", "Unexpected error: ${e.message}", e)
                success = false
            }
            success
        }
    }
}
