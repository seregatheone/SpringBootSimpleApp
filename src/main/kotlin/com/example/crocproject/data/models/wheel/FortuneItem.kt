package com.example.crocproject.data.models.wheel

data class FortuneItem (
    val id : String,
    val lotName : String,
    val totalSpentInRub: Int,
    val tickets: Int,
    val discountTickets : List<Pair<String,Int>>
        )