package com.example.crocproject.data


import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.web.multipart.MultipartFile


class ExcelFileParser(private val multipartFile : MultipartFile) {

    private val mapWithIndexes = mapOf(
        "id" to 0,
        "userCrocCode" to 1,
        "userDepartment" to 6,
        "isArchive" to 10,
        "availableBalance" to 13
    )

    //excel start
    private val workBook = XSSFWorkbook(multipartFile.inputStream)
    private val neededSheet = workBook.getSheetAt(0)!!
    fun parseExcelFileWithPIO() : MutableList<EmployeeModel>{
        val resultMutableList = mutableListOf<EmployeeModel>()
        val rowIterator = neededSheet.iterator()
        rowIterator.next()
        while(rowIterator.hasNext()){
            val row = rowIterator.next()
            val cellIterator = row.cellIterator()
            var id = 0
            var userCrocCode = ""
            var userDepartment = ""
            var isArchive = false
            var availableBalance = 0
            while(cellIterator.hasNext()){
                val cell = cellIterator.next()

                when(cell.columnIndex){
                    mapWithIndexes["id"] -> try {
                        id = cell.numericCellValue.toInt()
                    }catch (e : Exception){
                        print("wtf0")
                        println(e.stackTrace)
                        break
                    }
                    mapWithIndexes["userCrocCode"] -> userCrocCode = cell.stringCellValue
                    mapWithIndexes["userDepartment"] -> userDepartment = cell.stringCellValue
                    mapWithIndexes["isArchive"] -> {
                        try {
                            isArchive = when (cell.toString()) {
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
                        availableBalance = cell.numericCellValue.toInt()
                    }catch (e : Exception){
                        println(e.stackTrace)
                        break
                    }
                }
            }
            val employeeModel = EmployeeModel(id,userCrocCode,userDepartment,isArchive,availableBalance)
            resultMutableList.add(employeeModel)
        }
        return resultMutableList
    }
    //excel end

}
