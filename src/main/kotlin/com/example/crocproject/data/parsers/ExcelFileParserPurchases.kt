package com.example.crocproject.data.parsers

import com.example.crocproject.data.models.PurchasesDoneModel
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.web.multipart.MultipartFile

class ExcelFileParserPurchases(private val multipartFile : MultipartFile) {
    private val mapWithIndexes = mapOf(
        "userCrocCode" to 10,
        "login" to 11,
        "spent" to 7,
    )
    //excel start
    private val workBook = XSSFWorkbook(multipartFile.inputStream)
    private val neededSheet = workBook.getSheetAt(0)!!
    fun parseExcelFileWithPIO() : MutableList<PurchasesDoneModel>{
        val resultMutableList = mutableListOf<PurchasesDoneModel>()
        val rowIterator = neededSheet.iterator()
        rowIterator.next()
        while(rowIterator.hasNext()){
            val row = rowIterator.next()
            val cellIterator = row.cellIterator()

            var login = ""
            var userCrocCode = ""
            var spent = 0

            while(cellIterator.hasNext()){
                val cell = cellIterator.next()

                when(cell.columnIndex){
                    mapWithIndexes["login"] -> login = cell.stringCellValue
                    mapWithIndexes["userCrocCode"] -> userCrocCode = cell.stringCellValue
                    mapWithIndexes["spent"] -> try {
                        spent = cell.numericCellValue.toInt()
                    }catch (e : Exception){
                        println(e.stackTrace)
                        break
                    }
                }
            }
            val purchaseDoneModel = PurchasesDoneModel(
                login = login,
                userCrocCode = userCrocCode,
                moneyAmount = spent
            )
            resultMutableList.add(purchaseDoneModel)
        }
        return resultMutableList
    }
}