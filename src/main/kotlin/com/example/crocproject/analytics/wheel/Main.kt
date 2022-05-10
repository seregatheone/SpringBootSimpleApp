package com.example.crocproject.analytics.wheel

import kotlin.random.Random

fun main(){

    val tickets = 100
    val discounts = listOf(5,10,15,20)
    val price = 5000
    val budget = 35000



    fun calculateCost(map:Map<Int,Int>,price : Int):Int{
        var summa = 0
        map.forEach{ (percent, amount) ->
            summa+= percent * amount * price / 100
        }
        return summa
    }

    fun makeComputation(tickets : Int,
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

    println(makeComputation(tickets = tickets, discounts = discounts, price = price, budget = budget))
}