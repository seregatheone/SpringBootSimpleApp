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
