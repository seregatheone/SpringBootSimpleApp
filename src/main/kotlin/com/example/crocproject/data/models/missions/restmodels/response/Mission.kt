package com.example.crocproject.data.models.missions.restmodels.response

data class Mission(
    val budget: String,
    val fixedBusters: FixedBusters,
    val fixedTickets: Int,
    val missionIncome: String,
    val missionName: String,
    val multBusterTickets: Int,
    val multBusters: MultBusters,
    val percentBusterTickets: Int,
    val percentBusters: PercentBusters,
    val spent: Int,
    val tickets: Int
)