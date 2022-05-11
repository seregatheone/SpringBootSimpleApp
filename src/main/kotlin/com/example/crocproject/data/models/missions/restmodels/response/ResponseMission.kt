package com.example.crocproject.data.models.missions.restmodels.response

data class ResponseMission(
    val missions: List<MissionResponseItem>,
    val totalSpent: Int,
    val totalTickets: Int
)