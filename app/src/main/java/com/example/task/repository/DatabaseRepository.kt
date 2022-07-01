package com.example.task.repository

import android.content.Context
import com.example.task.database.DepartmentDao
import com.example.task.model.entity.Department
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DatabaseRepository@Inject constructor(
    private val departmentDao: DepartmentDao,
    var context: Context
) {
    fun getAllDepartments(): Flow<List<Department>> {
        return departmentDao.getAllDepartments()
    }
    fun getDepartmentById(id: Integer): Department {
        return departmentDao.getDepartmentById(id)
    }
    fun getDepartmentByName(name: String): Department {
        return departmentDao.getDepartmentByName(name)
    }
    suspend fun insertDepartment(department: Department) {
        departmentDao.insertDepartment(department)
    }
    suspend fun deleteDepartment(department: Department){
        departmentDao.deleteDepartment(department)
    }
    suspend fun deleteDepartmentByName(departmentName: String){
        departmentDao.deleteDepartmentByName(departmentName)
    }
}