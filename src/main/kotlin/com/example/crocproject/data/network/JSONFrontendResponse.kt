package com.example.crocproject.data.network

import com.google.gson.GsonBuilder
import krangl.max
import krangl.min
import krangl.toDoubles
import krangl.toInts

data class JSONFrontendResponse(
    val bobrDistribution: List<Int>,
    val departmentName: String,
    val giniCoefficient: String,
    val id: Int,
    val maximumBalance: String,
    val minimumBalance: String,
    val numberOfStaff: String
)
//"giniCoefficient" to {gini(it["availableBalance"].toDoubles().toList() as List<Double>).toString() },
//"numberOfStaff" to {it["availableBalance"].length.toString()},
//"maximumBalance" to {it["availableBalance"].max()!!.toInt().toString()},
//"minimumBalance" to {it["availableBalance"].min()!!.toInt().toString()},
//"id" to {k++},
//"bobrDistribution" to { GsonBuilder().create().toJson(it["availableBalance"].toInts())},
//"departmentName" to { it["userDepartment"][0]}