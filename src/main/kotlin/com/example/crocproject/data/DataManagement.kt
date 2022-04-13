package com.example.crocproject.data

import com.example.crocproject.analytics.AnalyticsAuction
import com.example.crocproject.data.models.DepartmentModel
import com.example.crocproject.data.models.MissionModel
import com.example.crocproject.data.models.PurchaseModel
import org.springframework.web.multipart.MultipartFile

class DataManagement(private val multipartFile : MultipartFile) {
    private val fileObserver = ExcelFileParser(multipartFile)
    private val listOfEmployers by lazy{ fileObserver.parseExcelFileWithPIO()}

    private var _departmentModelList: List<DepartmentModel>? = null
    val departmentModelList get() = _departmentModelList!!

    private var _missionModelList: List<MissionModel>? = null
    val missionModelList get() = _missionModelList!!

    private var _purchaseModelList: List<PurchaseModel>? = null
    val purchaseModelList get() = _purchaseModelList!!

    fun auctionAnalytics(){
        val analyticsAuction = AnalyticsAuction()
        analyticsAuction.makeDataframe(listOfEmployers)
        analyticsAuction.workWithDataframe()
        _departmentModelList = analyticsAuction.reformatToListResponse()
    }
    fun usersMission(){
        val analyticsAuction = AnalyticsAuction()
        analyticsAuction.makeDataframe(listOfEmployers)
        analyticsAuction.workWithDataframe()
//        _missionModelList = analyticsAuction.reformatToListResponse()
    }
    fun usersPurchases(){
        val analyticsAuction = AnalyticsAuction()
        analyticsAuction.makeDataframe(listOfEmployers)
        analyticsAuction.workWithDataframe()
//        _purchaseModelList = analyticsAuction.reformatToListResponse()
    }
}