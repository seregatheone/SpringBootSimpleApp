package com.example.crocproject.data

import com.example.crocproject.analytics.auction.AnalyticsAuction
import com.example.crocproject.data.models.parsermodels.department.DepartmentModel
import com.example.crocproject.data.parsers.ExcelFileParserDepartment
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.web.multipart.MultipartFile

class DepartmentManagement(private val multipartFile : MultipartFile) {
    private var _departmentModelList: List<DepartmentModel>? = null
    val departmentModelList get() = _departmentModelList!!

    fun auctionAnalytics(){
        val workBook = XSSFWorkbook(multipartFile.inputStream)
        val neededSheet = workBook.getSheetAt(0)!!

        val fileObserver = ExcelFileParserDepartment(neededSheet)
        val listOfEmployers = fileObserver.parseExcelFileWithPIO()

        val analyticsAuction = AnalyticsAuction(listOfEmployers)

        analyticsAuction.workWithDataframe()
        _departmentModelList = analyticsAuction.reformatToListResponse()
    }
}