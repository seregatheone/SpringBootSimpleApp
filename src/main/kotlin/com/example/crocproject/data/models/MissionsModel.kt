package com.example.crocproject.data.models

data class MissionsModel(
    val earned: Int,
    val login: String,
    val missionsCompleted: Int
)
data class MissionsCompletedModel(
    val moneyAmount: Int,
    val login: String
)