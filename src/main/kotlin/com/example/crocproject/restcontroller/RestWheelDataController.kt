package com.example.crocproject.restcontroller

import com.example.crocproject.analytics.wheel.DisloyalAnalyticsWheel
import com.example.crocproject.analytics.wheel.LoyalAnalyticsWheel
import com.example.crocproject.data.models.wheel.ResponseWheel
import com.example.crocproject.data.models.wheel.WheelGoodsRequestBody
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@CrossOrigin
@RestController
class RestWheelDataController {
    @GetMapping("/compute-wheel")
    fun computeWheel(wheelGoodsRequestBody : WheelGoodsRequestBody) : ResponseWheel{
        var result : ResponseWheel? = null
        when(wheelGoodsRequestBody.computationType){
            0 -> {
                val disloyalAnalyticsWheel = DisloyalAnalyticsWheel(wheelGoodsRequestBody)
                result = disloyalAnalyticsWheel.countTickets()
            }
            1 -> {
                val loyalAnalyticsWheel = LoyalAnalyticsWheel(wheelGoodsRequestBody)
            }
        }
        return when(result){
            null -> throw ResponseStatusException(HttpStatus.NOT_FOUND)
            else -> result
        }
    }

}