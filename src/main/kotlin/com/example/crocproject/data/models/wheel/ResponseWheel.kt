package com.example.crocproject.data.models.wheel

data class ResponseWheel (
    val spent : Int,
    val tickets: Int,
    val fortuneItems:List<FortuneItem>
        )