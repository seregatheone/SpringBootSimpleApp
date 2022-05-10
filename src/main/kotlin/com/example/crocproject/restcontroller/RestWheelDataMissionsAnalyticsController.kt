package com.example.crocproject.restcontroller


import com.example.crocproject.analytics.wheel.AnalyticsWheel
import com.example.crocproject.data.models.wheel.restmodels.ResponseWheel
import com.example.crocproject.data.models.wheel.restmodels.WheelGoodsRequestBody
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
class RestWheelDataMissionsAnalyticsController {
    @PostMapping("/compute-wheel")
    fun computeWheel(@RequestBody wheelGoodsRequestBody : WheelGoodsRequestBody) : ResponseWheel {
        val wheelAnalyticsClass = AnalyticsWheel(wheelGoodsRequestBody)
        return wheelAnalyticsClass.countTickets()
    }
//    @PostMapping("/compute-mission")
//    fun computeMission(@RequestBody requestMission : RequestMission) : ResponseMission {
//        val missionAnalytics = MissionsAnalytics(requestMission)
//        return missionAnalytics.countTickets()
//    }

}