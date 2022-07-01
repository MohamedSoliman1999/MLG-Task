package com.example.task.model.entity

import java.io.Serializable


open class Employee(
    var id: Int,
    var name: String,
    var email: String,
    var hireDate: String
): Serializable {

}