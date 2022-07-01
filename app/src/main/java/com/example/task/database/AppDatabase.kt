package com.example.task.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.task.model.entity.Department
import com.example.task.util.Constants.DEPARTMENT_DB_VERSION

@Database(
    entities = [Department::class],
    exportSchema = false,
    version = DEPARTMENT_DB_VERSION
)
@TypeConverters(DatabaseConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getDepartmentDAO(): DepartmentDao
}