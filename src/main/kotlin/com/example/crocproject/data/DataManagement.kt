package com.example.crocproject.data

import com.example.crocproject.analytics.MissionsDataFrame
import com.example.crocproject.analytics.PurchasesDataFrame
import com.example.crocproject.analytics.auction.AnalyticsAuction
import com.example.crocproject.data.models.*
import com.example.crocproject.data.parsers.ExcelFileParserDepartment
import com.example.crocproject.data.parsers.ExcelFileParserMissions
import com.example.crocproject.data.parsers.ExcelFileParserPurchases
import org.springframework.web.multipart.MultipartFile

class DataManagement(private val multipartFile : MultipartFile) {
    private var _departmentModelList: List<DepartmentModel>? = null
    val departmentModelList get() = _departmentModelList!!

    private var _missionModelList: List<MissionsModel>? = null
    val missionModelList get() = _missionModelList!!

    private var _purchaseModelList: List<PurchasesModel>? = null
    val purchaseModelList get() = _purchaseModelList!!

    fun auctionAnalytics(){
        val analyticsAuction = AnalyticsAuction()
        val fileObserver = ExcelFileParserDepartment(multipartFile)
        val listOfEmployers = fileObserver.parseExcelFileWithPIO()
        analyticsAuction.makeDataframe(listOfEmployers)
        analyticsAuction.workWithDataframe()
        _departmentModelList = analyticsAuction.reformatToListResponse()
    }
    fun usersMissions(){
        val missionsDataFrame = MissionsDataFrame()
        val fileObserver = ExcelFileParserMissions(multipartFile)
        val listOfCompletedMission = fileObserver.parseExcelFileWithPIO()
        missionsDataFrame.makeDataframe(listOfCompletedMission)
        missionsDataFrame.workWithDataframe()
        _missionModelList = missionsDataFrame.reformatToListResponse()
    }
    fun usersPurchases(){
        val purchasesDataFrame = PurchasesDataFrame()
        val fileObserver = ExcelFileParserPurchases(multipartFile)
        val listOfCompletedPurchases = fileObserver.parseExcelFileWithPIO()
        purchasesDataFrame.makeDataframe(listOfCompletedPurchases)
        purchasesDataFrame.workWithDataframe()
        _purchaseModelList = purchasesDataFrame.reformatToListResponse()
    }
}