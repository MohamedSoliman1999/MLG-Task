package com.example.task.ui.onlineemployee

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task.model.datatransfer.EmployeeDataResponse
import com.example.task.network.Resource
import com.example.task.repository.NetworkRepository
import com.squareup.okhttp.ResponseBody
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class OnlineEmployeeViewModel @Inject constructor(private val networkRepository: NetworkRepository) :
    ViewModel() {
    private val _employeeResponse =
        MutableStateFlow<Resource<EmployeeDataResponse>>(Resource.Idle())
    val employeeResponse: StateFlow<Resource<EmployeeDataResponse>>
        get() = _employeeResponse
    private var pageNumber = 0
    var canLoad = true
    fun getEmployeeData() {
        viewModelScope.launch {
            _employeeResponse.value = Resource.Loading()
             try {

                networkRepository.getEmployeeList(++pageNumber).enqueue(object :Callback<EmployeeDataResponse>{
                    override fun onResponse(
                        call: Call<EmployeeDataResponse>,
                        response: Response<EmployeeDataResponse>
                    ) {
                        Log.e("response",response.body().toString())
                        _employeeResponse.value = Resource.Success(response.body()!!)
                    }

                    override fun onFailure(call: Call<EmployeeDataResponse>, t: Throwable) {
                        Log.e("Error","${t.message}")
                        _employeeResponse.value = Resource.Error(t)
                    }

                })
            } catch (e: Exception) {
                 _employeeResponse.value =Resource.Error(e)
            }
        }
    }
}