package com.example.crocproject.data.models.wheel.restmodels

data class WheelGoodsRequestBody (
    val wheelNextTime:Int,
    val computationBudget:Int,
    val computationType:Int,
    val fortuneItems : List<WheelGoodsFortuneItems>
        )