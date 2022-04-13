package com.example.crocproject.data.models

data class PurchasesModel(
    val login: String,
    val numberOfPurchases: Int,
    val spent: Int,
    val userCrocCode: String
)
data class PurchasesDoneModel(
    val userCrocCode : String,
    val login : String,
    val moneyAmount : Int
)