package com.example.crocproject.data.models.missions.restmodels.response

data class MissionResponseItem(
    val budget: Int,
    val fixedBusters: List<FixedBusters>,
    val fixedTickets: Int,
    val missionIncome: Int,
    val missionName: String,
    val multBusterTickets: Int,
    val multBusters: List<MultBusters>,
    val percentBusterTickets: Int,
    val percentBusters: List<PercentBusters>,
    val spent: Int,
    val tickets: Int
)