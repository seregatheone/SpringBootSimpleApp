package com.example.crocproject.data.parsers


import com.example.crocproject.data.models.parsermodels.EmployeeModel
import org.apache.poi.xssf.usermodel.XSSFSheet


class ExcelFileParserDepartment(private val sheet : XSSFSheet) {

    private val rowIterator = sheet.iterator()
    private val titleRow = rowIterator.next()

    private val associatedTitleRow = titleRow.associate { it.stringCellValue to it.columnIndex }

    private val mapWithIndexes = mapOf(
        "id" to associatedTitleRow["id"],
        "userCrocCode" to associatedTitleRow["userCrocCode"],
        "userName" to associatedTitleRow["userName"],
        "userLastName" to associatedTitleRow["userLastName"],
        "userFirstName" to associatedTitleRow["userFirstName"],
        "userMiddleName" to associatedTitleRow["userMiddleName"],
        "userDepartment" to associatedTitleRow["userDepartment"],
        "isArchive" to associatedTitleRow["isArchive"],
        "availableBalance" to associatedTitleRow["availableBalance"]
    )

    //excel start
    fun parseExcelFileWithPIO() : MutableList<EmployeeModel>{
        val resultMutableList = mutableListOf<EmployeeModel>()
        val rowIterator = sheet.iterator()
        rowIterator.next()
        while(rowIterator.hasNext()){
            val row = rowIterator.next()
            val cellIterator = row.cellIterator()
            val employeeModel = EmployeeModel()
            while(cellIterator.hasNext()){
                val cell = cellIterator.next()

                when(cell.columnIndex){
                    mapWithIndexes["id"] -> try {
                        employeeModel.id = cell.numericCellValue.toInt()
                    }catch (e : Exception){
                        print("wtf0")
                        println(e.stackTrace)
                        break
                    }
                    mapWithIndexes["userName"] -> employeeModel.userName = cell.stringCellValue
                    mapWithIndexes["userLastName"] ->employeeModel.userLastName = cell.stringCellValue
                    mapWithIndexes["userFirstName"] ->employeeModel.userFirstName = cell.stringCellValue
                    mapWithIndexes["userMiddleName"] ->employeeModel.userMiddleName = cell.stringCellValue
                    mapWithIndexes["userCrocCode"] -> employeeModel.userCrocCode = cell.stringCellValue
                    mapWithIndexes["userDepartment"] -> employeeModel.userDepartment = cell.stringCellValue
                    mapWithIndexes["isArchive"] -> {
                        try {
                            employeeModel.isArchive = when (cell.toString()) {
                                "FALSE" -> false
                                "TRUE" -> true
                                else -> {
                                    print("wtf10")
                                    throw Exception("10 value in ExcelFileParser is not boolean")
                                }
                            }
                        }catch (e : Exception){
                            println(e.stackTrace)
                            break
                        }
                    }
                    mapWithIndexes["availableBalance"] -> try {
                        employeeModel.availableBalance = cell.numericCellValue.toInt()
                    }catch (e : Exception){
                        println(e.stackTrace)
                        break
                    }
                }
            }
            resultMutableList.add(employeeModel)
        }
        return resultMutableList
    }
    //excel end

}
