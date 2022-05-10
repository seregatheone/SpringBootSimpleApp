package com.example.crocproject.data.models.missions.restmodels.request

data class Mission(
    val budget: Int,
    val fixed: List<Int>,
    val id: Int,
    val missionIncome: Int,
    val missionName: String,
    val multBusters: List<Int>,
    val percentBusters: List<Int>
)