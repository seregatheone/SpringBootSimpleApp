package com.example.crocproject.data.models.wheel


data class WheelGoodsFortuneItems(
    val id : String,
    val lotName : String,
    val price : String,
    val tickets : String,
    val percentage : String,
    val budget : String,
    val discounts : List<String>
)