package com.example.crocproject.analytics.wheel

import com.example.crocproject.utils.calculateCost
import kotlin.random.Random

class DisloyalAnalyticsWheel: WheelAnalyticsClass() {

    //split tickets randomly according to cost and amount
    override fun makeComputation(tickets : Int,
                                 discounts : List<Int>,
                                 price : Int,
                                 budget : Int
    ): MutableMap<Int,Int>{
        val sortedDisc = discounts.sorted()
        val discountsMap = sortedDisc.associateWith { tickets/sortedDisc.size }.toMutableMap()
        for (i in 0..tickets*100) {
            val spent = calculateCost(discountsMap, price)
            if (spent > budget) {
                val cat = Random.nextInt(1, sortedDisc.size)
                val randomKey = discountsMap.keys.toList()[cat]
                if (discountsMap[randomKey]!! <= 0) {
                    continue
                }
                val randomKeyMinusOne = discountsMap.keys.toList()[cat-1]
                discountsMap.merge(randomKey,1,Int::minus)
                discountsMap.merge(randomKeyMinusOne,1,Int::plus)
            }
        }

        return discountsMap
    }
}
