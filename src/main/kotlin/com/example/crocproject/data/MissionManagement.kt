package com.example.crocproject.data

import com.example.crocproject.analytics.missions.MissionsDataFrame
import com.example.crocproject.data.models.parsermodels.mission.MissionsModel
import com.example.crocproject.data.parsers.ExcelFileParserMissions
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.web.multipart.MultipartFile

class MissionManagement(private val multipartFile : MultipartFile) {

    private var _missionModelList: List<MissionsModel>? = null
    val missionModelList get() = _missionModelList!!

    fun usersMissions(){


        val workBook = XSSFWorkbook(multipartFile.inputStream)
        val neededSheet = workBook.getSheetAt(0)!!

        val fileObserver = ExcelFileParserMissions(neededSheet)
        val listOfCompletedMission = fileObserver.parseExcelFileWithPIO()

        val missionsDataFrame = MissionsDataFrame(listOfCompletedMission)

        missionsDataFrame.workWithDataframe()
        _missionModelList = missionsDataFrame.reformatToListResponse()
    }
}