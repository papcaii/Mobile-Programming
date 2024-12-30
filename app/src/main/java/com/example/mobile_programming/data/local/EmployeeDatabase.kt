package com.example.mobile_programming.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Employee::class], version = 1, exportSchema = false)
abstract class EmployeeDatabase : RoomDatabase() {
    abstract fun employeeDao(): EmployeeDao

    companion object {
        @Volatile private var instance: EmployeeDatabase? = null

        fun getInstance(context: Context): EmployeeDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    EmployeeDatabase::class.java,
                    "employee_database"
                ).build().also { instance = it }
            }
        }
    }
}
