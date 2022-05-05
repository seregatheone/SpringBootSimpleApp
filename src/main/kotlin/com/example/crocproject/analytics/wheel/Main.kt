package com.example.crocproject.analytics.wheel

import jetbrains.datalore.plot.common.data.SeriesUtil.sum
import kotlinx.coroutines.*
import kotlin.random.Random

//fun main() {
//    val tickets = 100
//    val discounts = MutableList(16) { tickets / 16 }
//    discounts[15]+=tickets%16
//    val price = 5000
//    val budget = 30000
//    val scope = CoroutineScope(Dispatchers.Default)
//    scope.launch {
//        for (i in 0..tickets*1000) {
//            val spent = calculateCost(discounts, price)
//            if (spent > budget) {
//                val cat = Random.nextInt(1, 16)
//                if (discounts[cat] <= 0) {
//                    continue
//                }
//                discounts[cat] -= 1
//                discounts[cat - 1] += 1
//
//            }
//        }
//    }.invokeOnCompletion {
//        discounts.forEach {
//            println(it)
//        }
//        println(calculateCost(discounts,price))
//        println(sum(discounts.map{it.toDouble()}))
//    }
//
//    Thread.sleep(3000)
//
//}
//fun calculateCost(list:List<Int>,price : Int):Int{
//    var summa = 0
//    list.forEachIndexed { index, i ->
//        summa+= i * index * price / 20
//    }
//    return summa
//}