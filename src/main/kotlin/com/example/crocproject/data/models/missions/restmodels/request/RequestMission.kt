package com.example.crocproject.data.models.missions.restmodels.request

data class RequestMission(
    val activeOptionId: String,
    val computationType: Int,
    val missions: List<Mission>,
    val totalTickets: Int,
    val wheelNextTime: Int
)