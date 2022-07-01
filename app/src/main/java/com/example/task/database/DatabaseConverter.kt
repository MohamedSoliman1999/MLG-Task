package com.example.task.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.task.model.entity.Employee
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Serializable
import java.lang.reflect.Type

open class DatabaseConverter: Serializable {
    @TypeConverter
    fun fromFieldsListToGson(fieldsList: List<Employee>?):String{
        return Gson().toJson(fieldsList)
    }
    @TypeConverter
    fun fromGsonToFields(gsonFields: String):List<Employee>?{
        val listType: Type = object : TypeToken<List<Employee?>?>() {}.type
        return Gson().fromJson(gsonFields, listType)
    }
//
//    @TypeConverter
//    open fun fromEmployeeList(employees: List<Employee?>?): String? {
//        if (employees == null) {
//            return null
//        }
//        val gson = Gson()
//        val type =
//            object : TypeToken<List<Employee?>?>() {}.type
//        return gson.toJson(employees, type)
//    }
//
//    @TypeConverter
//    open fun toEmployeeList(employeesLangString: String?): List<Employee>? {
//        if (employeesLangString == null) {
//            return null
//        }
//        val gson = Gson()
//        val type =
//            object : TypeToken<List<Employee?>?>() {}.type
//        return gson.fromJson<List<Employee>>(employeesLangString, type)
//    }
}