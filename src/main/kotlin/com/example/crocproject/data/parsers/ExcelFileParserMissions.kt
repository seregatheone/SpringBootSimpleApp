package com.example.crocproject.data.parsers

import com.example.crocproject.data.models.MissionsCompletedModel
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.web.multipart.MultipartFile

class ExcelFileParserMissions(private val multipartFile : MultipartFile) {
    private val mapWithIndexes = mapOf(
        "login" to 0,
        "earned" to 6
    )
    //excel start
    private val workBook = XSSFWorkbook(multipartFile.inputStream)
    private val neededSheet = workBook.getSheetAt(0)!!
    fun parseExcelFileWithPIO() : MutableList<MissionsCompletedModel>{
        val resultMutableList = mutableListOf<MissionsCompletedModel>()
        val rowIterator = neededSheet.iterator()
        rowIterator.next()
        while(rowIterator.hasNext()){
            val row = rowIterator.next()
            val cellIterator = row.cellIterator()

            var login = ""
            var earned = 0
            while(cellIterator.hasNext()){
                val cell = cellIterator.next()

                when(cell.columnIndex){
                    mapWithIndexes["login"] -> login = cell.stringCellValue
                    mapWithIndexes["earned"] -> try {
                        earned = cell.numericCellValue.toInt()
                    }catch (e : Exception){
                        println(e.stackTrace)
                        break
                    }
                }
            }
            val missionCompletedModel = MissionsCompletedModel(
                login = login,
                moneyAmount = earned
            )
            resultMutableList.add(missionCompletedModel)
        }
        return resultMutableList
    }
}