package com.example.crocproject.analytics.wheel

import com.example.crocproject.data.models.wheel.FortuneItem
import com.example.crocproject.data.models.wheel.ResponseWheel
import com.example.crocproject.data.models.wheel.WheelGoodsRequestBody
import kotlinx.coroutines.*
import kotlin.random.Random

class DisloyalAnalyticsWheel: WheelAnalyticsClass() {

    private fun calculateCost(map:Map<Int,Int>,price : Int):Int{
        var summa = 0
        map.forEach{ (percent, amount) ->
            summa+= percent * amount * price / 100
        }
        return summa
    }

    //split tickets randomly according to cost and amount
    override fun makeComputation(tickets : Int,
                                 discounts : List<Int>,
                                 price : Int,
                                 budget : Int
    ): MutableMap<Int,Int>{
        val discountsMap = discounts.associateWith { tickets/discounts.size }.toMutableMap()
        for (i in 0..tickets*100) {
            val spent = calculateCost(discountsMap, price)
            if (spent > budget) {
                val cat = Random.nextInt(0, discounts.size)
                val randomKey = discountsMap.keys.toList()[cat]
                if (discounts[randomKey]!! <= 0) {
                    continue
                }
                discountsMap.merge(randomKey,1,Int::minus)
                discountsMap.merge(randomKey-1,1,Int::plus)
            }
        }
        return discountsMap
    }
}
