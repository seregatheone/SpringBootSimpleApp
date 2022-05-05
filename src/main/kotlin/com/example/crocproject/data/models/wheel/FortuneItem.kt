package com.example.crocproject.data.models.wheel

data class FortuneItem (
    val id : String,
    val lotName : String,
    val discountTickets : List<Pair<String,Int>>
        )