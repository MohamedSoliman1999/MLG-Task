package com.example.task.network

import com.example.task.model.datatransfer.EmployeeDataResponse
import com.squareup.okhttp.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface EmployeeApi {
    //@FormUrlEncoded
    @GET("users")
    fun getEmployees(@Query("page") page: Int? = 1): Call<EmployeeDataResponse>
}