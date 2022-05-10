package com.example.crocproject.data.models.missions.restmodels.response

data class ResponseMission(
    val missions: List<Mission>,
    val totalSpent: Int,
    val totalTickets: Int
)