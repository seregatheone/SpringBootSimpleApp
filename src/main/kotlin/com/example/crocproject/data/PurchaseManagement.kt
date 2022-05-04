package com.example.crocproject.data

import com.example.crocproject.analytics.auction.PurchasesDataFrame
import com.example.crocproject.data.models.aucton.parsermodels.purchases.PurchasesModel
import com.example.crocproject.data.parsers.ExcelFileParserPurchases
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.web.multipart.MultipartFile

class PurchaseManagement(private val multipartFile : MultipartFile) {

    private var _purchaseModelList: List<PurchasesModel>? = null
    val purchaseModelList get() = _purchaseModelList!!

    fun usersPurchases(){


        val workBook = XSSFWorkbook(multipartFile.inputStream)
        val neededSheet = workBook.getSheetAt(0)!!

        val fileObserver = ExcelFileParserPurchases(neededSheet)
        val listOfCompletedPurchases = fileObserver.parseExcelFileWithPIO()

        val purchasesDataFrame = PurchasesDataFrame(listOfCompletedPurchases)

        purchasesDataFrame.workWithDataframe()
        _purchaseModelList = purchasesDataFrame.reformatToListResponse()
    }

}