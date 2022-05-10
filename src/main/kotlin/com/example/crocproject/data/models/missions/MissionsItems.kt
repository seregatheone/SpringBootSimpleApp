package com.example.crocproject.data.models.missions

data class MissionsItem (
    val id : Int,
    val missionName : String,
    val budget : Int,
    val missionIncome : Int,
    val percentBusters : List<Int>,
    val multiplyBusters : List<Int>,
    val fixed : List<Int>
        )