package com.example.crocproject.data.models.wheel


data class WheelGoodsFortuneItems(
    val id : Int,
    val name : String,
    val price : Int,
    val tickets : Int,
    val percentage : Int,
    val budget : Int,
    val discounts : List<String>
)