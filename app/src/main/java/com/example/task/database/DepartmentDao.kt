package com.example.task.database

import androidx.room.*
import com.example.task.model.entity.Department
import kotlinx.coroutines.flow.Flow

@Dao
interface DepartmentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDepartment(department: Department)
    @Delete
    suspend fun deleteDepartment(department: Department)
    @Query("DELETE FROM DepartmentTable WHERE name = :departmentName")
    suspend fun deleteDepartmentByName(departmentName: String)
    @Query("SELECT * FROM DepartmentTable")
    fun getAllDepartments(): Flow<List<Department>>
    @Query("SELECT * FROM DepartmentTable where id=:id")
    fun getDepartmentById(id:Integer):Department
    @Query("SELECT * FROM DepartmentTable where name=:name")
    fun getDepartmentByName(name:String):Department
}