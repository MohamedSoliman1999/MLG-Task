package com.example.task.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task.model.entity.Department
import com.example.task.repository.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val databaseRepository: DatabaseRepository
) : ViewModel() {
    val allDepartment = databaseRepository.getAllDepartments()
    fun insertDepartment(department: Department) = viewModelScope.launch {
        databaseRepository.insertDepartment(department)
    }
    fun deleteDepartment(department: Department) = viewModelScope.launch {
        databaseRepository.deleteDepartment(department)
    }
    fun deleteDepartmentByName(departmentName: String) = viewModelScope.launch {
        databaseRepository.deleteDepartmentByName(departmentName)
    }
}