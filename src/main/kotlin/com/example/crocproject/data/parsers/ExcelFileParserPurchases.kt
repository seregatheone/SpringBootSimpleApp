package com.example.crocproject.data.parsers

import com.example.crocproject.data.models.aucton.parsermodels.purchases.PurchasesDoneModel
import org.apache.poi.xssf.usermodel.XSSFSheet

class ExcelFileParserPurchases(private val sheet : XSSFSheet) {

    private val rowIterator = sheet.iterator()
    private val titleRow = rowIterator.next()

    private val associatedTitleRow = titleRow.associate { it.stringCellValue to it.columnIndex }

    private val mapWithIndexes = mapOf(
        "spent" to associatedTitleRow["Сумма"],
        "userCrocCode" to associatedTitleRow["КрокКод пользователя"],
        "login" to associatedTitleRow["Логин"]
    )
    //excel start
    fun parseExcelFileWithPIO() : MutableList<PurchasesDoneModel>{
        val resultMutableList = mutableListOf<PurchasesDoneModel>()
        val rowIterator = sheet.iterator()
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