package com.example.crocproject.analytics

import com.beust.klaxon.Klaxon
import com.example.crocproject.data.EmployeeModel
import com.example.crocproject.data.network.JSONFrontendResponse
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import krangl.*
import kotlin.math.abs

class AnalyticsAuction {

    private var _dataFrame: DataFrame? = null
    private val dataFrame get() = _dataFrame!!

    fun makeDataframe(list: List<EmployeeModel>) {
        _dataFrame = list.asDataFrame()
    }

    fun workWithDataframe() {
        var k = 0;
        _dataFrame = dataFrame
            .filter { it["isArchive"] eq false }
            .groupBy(by = arrayOf("userDepartment"))
            .summarize(
                "giniCoefficient" to { gini(it["availableBalance"].toDoubles().toList() as List<Double>).toString() },
                "numberOfStaff" to { it["availableBalance"].length.toString() },
                "maximumBalance" to { it["availableBalance"].max()!!.toInt().toString() },
                "minimumBalance" to { it["availableBalance"].min()!!.toInt().toString() },
                "id" to { k++ },
                "bobrDistribution" to { GsonBuilder().create().toJson(it["availableBalance"].toInts()) },
                "departmentName" to { it["userDepartment"][0] }
            )
            .remove("userDepartment")
    }

    fun gini(values: List<Double>): Double {
        val sumOfDifference = values.stream()
            .flatMapToDouble { v1: Double ->
                values.stream().mapToDouble { v2: Double -> abs(v1 - v2) }
            }.sum()
        val mean = values.stream().mapToDouble { v -> v }.average().asDouble
        return sumOfDifference / (2 * values.size * values.size * mean)
    }

    @Suppress("UNCHECKED_CAST")
    fun reformatToListResponse(): List<JSONFrontendResponse> {
        val outputDataFrameIterator = dataFrame.rows.iterator()
        val listOfResponses: MutableList<JSONFrontendResponse?> = mutableListOf()
        while (outputDataFrameIterator.hasNext()) {
            val mapOfData = outputDataFrameIterator.next().toMap()
            val sType = object : TypeToken<List<Int>>() { }.type
            println()
            val jSONFrontendResponse = JSONFrontendResponse(
                id = mapOfData["id"].toString().toInt(),
                bobrDistribution = Gson().fromJson(mapOfData["bobrDistribution"].toString(),sType),
                departmentName = mapOfData["departmentName"].toString(),
                giniCoefficient = mapOfData["giniCoefficient"].toString(),
                maximumBalance = mapOfData["maximumBalance"].toString(),
                minimumBalance = mapOfData["minimumBalance"].toString(),
                numberOfStaff = mapOfData["numberOfStaff"].toString()
            )
            listOfResponses.add(jSONFrontendResponse)
        }
        return listOfResponses as List<JSONFrontendResponse>
    }
}
