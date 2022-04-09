package com.example.crocproject.restcontroller

import com.example.crocproject.data.DataManagement
import com.example.crocproject.data.network.JSONFrontendResponse
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@CrossOrigin
@RestController
class RestFileController{

    @PostMapping("/uploadFile/auction")
    fun uploadFileAuction(@RequestParam("file") multipartFile: MultipartFile):List<JSONFrontendResponse>{
        val dataManagement = DataManagement(multipartFile)
        dataManagement.auctionAnalytics()
        return dataManagement.resultList
    }

    @GetMapping("/status")
    fun writeHello() : String{
        return "Status"
    }
}
