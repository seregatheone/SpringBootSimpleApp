package com.example.crocproject.data.models


data class DepartmentModel(
    val staff: List<UserModel>,
    val departmentName: String,
    val giniCoefficient: String,
    val id: Int,
    val maximumBalance: String,
    val minimumBalance: String,
    val numberOfStaff: String
)
data class UserModel(
    var availableBalance: Int = 0,
    var userCrocCode: String = "",
    var userFirstName: String = "",
    var userLastName: String = "",
    var userMiddleName: String = "",
    var userName: String = ""
)
