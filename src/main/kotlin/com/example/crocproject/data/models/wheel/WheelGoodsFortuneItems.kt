package com.example.crocproject.data.models.wheel


data class WheelGoodsFortuneItems(
    val id : String,
    val lotName : String,
    val price : Int,
    val tickets : Int,
    val percentage : Int,
    val discounts : List<Int>
)