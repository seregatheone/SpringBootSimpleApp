package com.example.crocproject.restcontroller

import com.example.crocproject.data.DataManagement
import com.example.crocproject.data.models.DepartmentModel
import com.example.crocproject.data.models.MissionModel
import com.example.crocproject.data.models.PurchaseModel
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@CrossOrigin
@RestController
class RestFileController{

    @PostMapping("/uploadFile/auction")
    fun uploadFileAuction(@RequestParam("file") multipartFile: MultipartFile):List<DepartmentModel>{
        val dataManagement = DataManagement(multipartFile)
        dataManagement.auctionAnalytics()
        return dataManagement.departmentModelList
    }
    @PostMapping("/uploadFile/missions")
    fun uploadFileMissions(@RequestParam("file") multipartFile: MultipartFile):List<MissionModel>{
        val dataManagement = DataManagement(multipartFile)
//        dataManagement.auctionAnalytics()
        return dataManagement.missionModelList
    }
    @PostMapping("/uploadFile/purchases")
    fun uploadFilePurchases(@RequestParam("file") multipartFile: MultipartFile):List<PurchaseModel>{
        val dataManagement = DataManagement(multipartFile)
//        dataManagement.auctionAnalytics()
        return dataManagement.purchaseModelList
    }

    @GetMapping("/status")
    fun writeHello() : String{
        return "Status"
    }
}
