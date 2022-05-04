package com.example.crocproject.restcontroller

import com.example.crocproject.analytics.wheel.DisloyalAnalyticsWheel
import com.example.crocproject.analytics.wheel.LoyalAnalyticsWheel
import com.example.crocproject.data.models.wheel.WheelGoodsRequestBody
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin
@RestController
class RestWheelDataController {
    @GetMapping("/compute-wheel")
    fun computeWheel(wheelGoodsRequestBody : WheelGoodsRequestBody){
        when(wheelGoodsRequestBody.computationType){
            0 -> {
                val disloyalAnalyticsWheel = DisloyalAnalyticsWheel(wheelGoodsRequestBody)
            }
            1 -> {
                val loyalAnalyticsWheel = LoyalAnalyticsWheel(wheelGoodsRequestBody)
            }
        }
        return
    }

}