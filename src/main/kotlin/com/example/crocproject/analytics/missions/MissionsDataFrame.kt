package com.example.crocproject.analytics.missions

import com.example.crocproject.data.models.parsermodels.mission.MissionsCompletedModel
import com.example.crocproject.data.models.parsermodels.mission.MissionsModel
import com.example.crocproject.data.models.parsermodels.purchases.PurchasesDoneModel
import krangl.*

class MissionsDataFrame(private val list: List<MissionsCompletedModel>) {

    private var _dataFrameMissions: DataFrame?
    private val dataFrameMissions get() = _dataFrameMissions!!

    init{
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