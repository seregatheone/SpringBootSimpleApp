package com.example.crocproject.restcontroller

import com.example.crocproject.analytics.wheel.AnalyticsWheel
import com.example.crocproject.data.models.missions.MissionCalculationRequestBody
import com.example.crocproject.data.models.wheel.restmodels.ResponseWheel
import com.example.crocproject.data.models.wheel.restmodels.WheelGoodsRequestBody
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@CrossOrigin
@RestController
class RestWheelDataMissionsAnalyticsController {
    @GetMapping("/compute-wheel")
    fun computeWheel(@RequestBody wheelGoodsRequestBody : WheelGoodsRequestBody) : ResponseWheel {
        val wheelAnalyticsClass = AnalyticsWheel(wheelGoodsRequestBody)
        return wheelAnalyticsClass.countTickets()
    }
    @GetMapping("/compute-mission")
    fun computeMission(@RequestBody missionCalculationRequestBody : MissionCalculationRequestBody){
    }

}