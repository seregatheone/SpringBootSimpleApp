package com.example.crocproject.analytics

import com.example.crocproject.data.models.MissionsCompletedModel
import com.example.crocproject.data.models.MissionsModel
import com.example.crocproject.data.models.PurchasesModel
import krangl.*
import java.lang.Integer.sum

class MissionsDataFrame {
    private var _dataFrameMissions: DataFrame? = null
    private val dataFrameMissions get() = _dataFrameMissions!!

    fun makeDataframe(list: List<MissionsCompletedModel>) {
        _dataFrameMissions = list.asDataFrame()
    }
    fun workWithDataframe() {
        _dataFrameMissions = dataFrameMissions
            .groupBy(by = arrayOf("login"))
            .summarize(
                "earned" to {it["moneyAmount"].toInts().sumOf { el -> el!! }},
                "count" to {it["moneyAmount"].toInts().size}
            )
    }
    fun reformatToListResponse(): List<MissionsModel> {
        val outputDataFrameIterator = dataFrameMissions.rows.iterator()
        val listOfResponses: MutableList<MissionsModel?> = mutableListOf()
        while (outputDataFrameIterator.hasNext()) {
            val mapOfData = outputDataFrameIterator.next().toMap()
            val missionsModel = MissionsModel(
                login = mapOfData["login"].toString(),
                earned = mapOfData["earned"].toString().toInt(),
                missionsCompleted = mapOfData["count"].toString().toInt()
            )
            listOfResponses.add(missionsModel)
        }
        return listOfResponses as List<MissionsModel>
    }
}