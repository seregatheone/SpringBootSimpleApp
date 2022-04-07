package com.example.crocproject.data

import com.example.crocproject.analytics.AnalyticsAuction
import com.example.crocproject.data.network.JSONFrontendResponse
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import krangl.DataFrame
import org.springframework.web.multipart.MultipartFile

class DataController(private val multipartFile : MultipartFile) {
    private val fileObserver = ExcelFileParser(multipartFile)
    private val listOfEmployers by lazy{ fileObserver.parseExcelFileWithPIO()}

    private var _resultList: List<JSONFrontendResponse>? = null
    val resultList get() = _resultList!!

    fun auctionAnalytics(){
        val analyticsAuction = AnalyticsAuction()
        analyticsAuction.makeDataframe(listOfEmployers)
        analyticsAuction.workWithDataframe()
        _resultList = analyticsAuction.reformatToListResponse()
//            JsonParser().parse()
    }
}