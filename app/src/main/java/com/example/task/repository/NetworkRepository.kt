package com.example.task.repository

import android.content.Context
import com.example.task.database.DepartmentDao
import com.example.task.network.EmployeeApi
import javax.inject.Inject

class NetworkRepository@Inject constructor(
    private val employeeApi: EmployeeApi,
    var context: Context
)  {
    fun getEmployeeList(pageNumber:Int) = employeeApi.getEmployees(pageNumber)
}