package com.example.crocproject.data.models.missions

data class MissionCalculationRequestBody(
    val wheelNextTime:Int,
    val totalTickets:Int,
    val computationType:Int,
    val activeOptionId : String,
    val missions : List<MissionsItem>
)