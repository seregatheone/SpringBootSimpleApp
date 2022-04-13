package com.example.crocproject.restcontroller

import com.example.crocproject.data.DataManagement
import com.example.crocproject.data.models.*
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@CrossOrigin
@RestController
class RestFileController{

    @PostMapping("/uploadFile/departments")
    fun uploadFileAuction(@RequestParam("file") multipartFile: MultipartFile):List<DepartmentModel>{
        val dataManagement = DataManagement(multipartFile)
        dataManagement.auctionAnalytics()
        return dataManagement.departmentModelList
    }
    @PostMapping("/uploadFile/missions")
    fun uploadFileMissions(@RequestParam("file") multipartFile: MultipartFile):List<MissionsModel>{
        val dataManagement = DataManagement(multipartFile)
        dataManagement.usersMissions()
        return dataManagement.missionModelList
    }
    @PostMapping("/uploadFile/purchases")
    fun uploadFilePurchases(@RequestParam("file") multipartFile: MultipartFile):List<PurchasesModel>{
        val dataManagement = DataManagement(multipartFile)
        dataManagement.usersPurchases()
        return dataManagement.purchaseModelList
    }

    @GetMapping("/status")
    fun writeHello() : String{
        return "Status"
    }
}
