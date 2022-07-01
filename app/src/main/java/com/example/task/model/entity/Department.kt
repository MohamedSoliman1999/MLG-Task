package com.example.task.model.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "DepartmentTable",
    indices = [Index(value = ["name"], unique = true), Index(value = ["id"], unique = true)])
class Department: Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Integer?=null
    var name: String
    var employees:List<Employee>?=null
    @Ignore
    constructor( name: String, employees: List<Employee>?)  {
        this.name = name
        this.employees = employees
    }
    constructor( id: Integer,name: String, employees: List<Employee>?)  {
        this.id = id
        this.name = name
        this.employees = employees
    }
}