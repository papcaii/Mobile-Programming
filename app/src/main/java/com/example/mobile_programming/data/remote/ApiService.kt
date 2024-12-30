package com.example.mobile_programming.data.remote

import com.example.mobile_programming.data.local.Employee
import retrofit2.http.GET

interface ApiService {
    @GET("employees") // Replace with your actual endpoint
    suspend fun getEmployees(): List<Employee>
}
