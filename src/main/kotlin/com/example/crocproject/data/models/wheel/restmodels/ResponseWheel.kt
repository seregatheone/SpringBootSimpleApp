package com.example.crocproject.data.models.wheel.restmodels

data class ResponseWheel (
    val spent : Int,
    val tickets: Int,
    val fortuneItems:List<FortuneItem>
        )