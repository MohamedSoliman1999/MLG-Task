package com.example.task.di

import android.content.Context
import com.example.task.database.DepartmentDao
import com.example.task.network.EmployeeApi
import com.example.task.repository.DatabaseRepository
import com.example.task.repository.NetworkRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideDatabaseRepository(
        @ApplicationContext context: Context,
        departmentDao: DepartmentDao
    ): DatabaseRepository {
        return DatabaseRepository(departmentDao, context)
    }
    @Provides
    @Singleton
    fun provideNetworkRepository(
        @ApplicationContext context: Context,
        employeeApi: EmployeeApi
    ): NetworkRepository {
        return NetworkRepository(employeeApi, context)
    }
}