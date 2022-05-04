package com.example.crocproject.analytics.auction

import com.example.crocproject.data.models.aucton.parsermodels.purchases.PurchasesDoneModel
import com.example.crocproject.data.models.aucton.parsermodels.purchases.PurchasesModel
import krangl.*

class PurchasesDataFrame(private val list: List<PurchasesDoneModel>) {

    private var _dataFramePurchases: DataFrame?
    private val dataFramePurchases get() = _dataFramePurchases!!

    init{
        _dataFramePurchases = list.asDataFrame()
    }

    fun workWithDataframe() {
        _dataFramePurchases = dataFramePurchases
            .groupBy(by = arrayOf("login","userCrocCode"))
            .summarize(
                "spent" to {it["moneyAmount"].toInts().sumOf { el -> el!! }},
                "count" to {it["moneyAmount"].toInts().size}
            )
    }
    @Suppress("UNCHECKED_CAST")
    fun reformatToListResponse(): List<PurchasesModel> {
        val outputDataFrameIterator = dataFramePurchases.rows.iterator()
        val listOfResponses: MutableList<PurchasesModel?> = mutableListOf()
        while (outputDataFrameIterator.hasNext()) {
            val mapOfData = outputDataFrameIterator.next().toMap()
            val purchasesModel = PurchasesModel(
                login = mapOfData["login"].toString(),
                numberOfPurchases = mapOfData["count"].toString().toInt(),
                spent = mapOfData["spent"].toString().toInt(),
                userCrocCode = mapOfData["userCrocCode"].toString()
            )
            listOfResponses.add(purchasesModel)
        }
        return listOfResponses as List<PurchasesModel>
    }
}