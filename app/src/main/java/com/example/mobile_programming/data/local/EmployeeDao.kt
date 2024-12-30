package com.example.mobile_programming.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface EmployeeDao {

    @Query("SELECT * FROM employees")
    fun getAllEmployees(): LiveData<List<Employee>>  // This is for observing in the UI

    @Query("SELECT * FROM employees")
    suspend fun getAllEmployeesSync(): List<Employee>  // This is for synchronous DB access

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmployees(employees: List<Employee>)

    @Query("DELETE FROM employees")
    suspend fun clearEmployees()
}

