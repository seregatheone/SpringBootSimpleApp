package com.example.crocproject.data


import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.web.multipart.MultipartFile


//EmployeeModel(                          columnId
//  private val id : Int,                | 0
//  private val userCrocCode:String,     | 1
//  private val userDepartment : String, | 6
//  private val isArchive : Boolean,     | 10
//  private val availableBalance : Int)  | 13
private const val FILE_NAME = "src/main/resources/fileToCreate.txt"
class ExcelFileParser(private val multipartFile : MultipartFile) {

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
                    0 -> try {
                        id = cell.numericCellValue.toInt()
                    }catch (e : Exception){
                        print("wtf0")
                        println(e.stackTrace)
                        break
                    }
                    1 -> userCrocCode = cell.stringCellValue
                    6 -> userDepartment = cell.stringCellValue
                    10 -> {
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
                    13 -> try {
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
