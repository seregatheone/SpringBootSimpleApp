package com.example.crocproject.restcontroller

import com.example.crocproject.data.DataController
import com.example.crocproject.data.network.JSONFrontendResponse
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile


@RestController
class RestFileController{
    @PostMapping("/uploadFile/auction")
    fun uploadFileAuction(@RequestParam("file") multipartFile: MultipartFile):List<JSONFrontendResponse>{
        val dataController = DataController(multipartFile)
        dataController.auctionAnalytics()
        return dataController.resultList
    }

    @GetMapping("/status")
    fun writeHello() : String{
        return "Status"
    }
}
