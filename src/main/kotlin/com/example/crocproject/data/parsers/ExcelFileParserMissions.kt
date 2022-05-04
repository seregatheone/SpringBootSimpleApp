package com.example.crocproject.data.parsers

import com.example.crocproject.data.models.aucton.parsermodels.mission.MissionsCompletedModel
import org.apache.poi.xssf.usermodel.XSSFSheet

class ExcelFileParserMissions(private val sheet : XSSFSheet) {

    private val rowIterator = sheet.iterator()
    private val titleRow = rowIterator.next()

    private val associatedTitleRow = titleRow.associate { it.stringCellValue to it.columnIndex }

    private val mapWithIndexes = mapOf(
        "login" to associatedTitleRow["Логин"],
        "earned" to  associatedTitleRow["Вознаграждение"]
    )
    //excel start
    fun parseExcelFileWithPIO() : MutableList<MissionsCompletedModel>{
        val resultMutableList = mutableListOf<MissionsCompletedModel>()

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