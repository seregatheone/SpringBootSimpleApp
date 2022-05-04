package com.example.crocproject.restcontroller

import com.example.crocproject.data.DepartmentManagement
import com.example.crocproject.data.MissionManagement
import com.example.crocproject.data.PurchaseManagement
import com.example.crocproject.data.models.aucton.parsermodels.department.DepartmentModel
import com.example.crocproject.data.models.aucton.parsermodels.mission.MissionsModel
import com.example.crocproject.data.models.aucton.parsermodels.purchases.PurchasesModel
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@CrossOrigin
@RestController
class RestFileController{

    @PostMapping("/uploadFile/departments")
    fun uploadFileAuction(@RequestParam("file") multipartFile: MultipartFile):List<DepartmentModel>{
        val departmentManagement = DepartmentManagement(multipartFile)
        departmentManagement.auctionAnalytics()
        return departmentManagement.departmentModelList
    }
    @PostMapping("/uploadFile/missions")
    fun uploadFileMissions(@RequestParam("file") multipartFile: MultipartFile):List<MissionsModel>{
        val missionManagement = MissionManagement(multipartFile)
        missionManagement.usersMissions()
        return missionManagement.missionModelList
    }
    @PostMapping("/uploadFile/purchases")
    fun uploadFilePurchases(@RequestParam("file") multipartFile: MultipartFile):List<PurchasesModel>{
        val dataManagement = PurchaseManagement(multipartFile)
        dataManagement.usersPurchases()
        return dataManagement.purchaseModelList
    }

    @GetMapping("/status")
    fun writeHello() : String{
        return "Status"
    }
}


