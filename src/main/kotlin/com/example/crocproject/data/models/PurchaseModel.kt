package com.example.crocproject.data.models

data class PurchaseModel(
    val login: String,
    val numberOfPurchases: Int,
    val spent: Int,
    val userCrocCode: String
)