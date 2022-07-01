package com.example.task.ui.employee

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task.model.entity.Department
import com.example.task.repository.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeeViewModel@Inject constructor(private val databaseRepository: DatabaseRepository):ViewModel() {
    val department = MutableSharedFlow<Department>()
    fun insertDepartment(department: Department) = viewModelScope.launch {
        databaseRepository.insertDepartment(department)
    }
    fun deleteDepartment(department: Department) = viewModelScope.launch {
        databaseRepository.deleteDepartment(department)
    }
    fun deleteDepartmentByName(departmentName: String) = viewModelScope.launch {
        databaseRepository.deleteDepartmentByName(departmentName)
    }
    fun getDepartmentByName(departmentName: String) = CoroutineScope(Dispatchers.IO).launch {
        department.emit(databaseRepository.getDepartmentByName(departmentName))
    }
    fun saveNewEmployID(employID: Int) = viewModelScope.launch {
        var sharedPreferences: SharedPreferences =
            databaseRepository.context.getSharedPreferences("sharedPrefs", MODE_PRIVATE)
        sharedPreferences.edit().putInt("Emp_id", employID).apply()
    }
    fun getLastEmployID():Int{
        var sharedPreferences: SharedPreferences =
            databaseRepository.context.getSharedPreferences("sharedPrefs", MODE_PRIVATE)
        return sharedPreferences.getInt("Emp_id",0 )
    }

}