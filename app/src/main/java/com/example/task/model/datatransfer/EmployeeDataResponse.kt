package com.example.task.model.datatransfer

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class EmployeeDataResponse(
    @field:Json(name = "data")
    val `data`: List<EmployeeResponse>,
    @field:Json(name = "page")
    val page: Int,
    @field:Json(name = "per_page")
    val per_page: Int,
    @field:Json(name = "support")
    val support: Support,
    @field:Json(name = "total")
    val total: Int,
    @field:Json(name = "total_pages")
    val total_pages: Int
): Parcelable
@Parcelize
data class EmployeeResponse(
    @field:Json(name = "avatar")
    val avatar: String,
    @field:Json(name = "email")
    val email: String,
    @field:Json(name = "first_name")
    val first_name: String,
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "last_name")
    val last_name: String
): Parcelable
@Parcelize
data class Support(
    @field:Json(name = "text")
    val text: String,
    @field:Json(name = "url")
    val url: String
): Parcelable