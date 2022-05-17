package com.example.crocproject.analytics.wheel

import com.example.crocproject.utils.calculateCost
import jetbrains.datalore.plot.common.data.SeriesUtil.sum
import smile.math.BFGS
import kotlin.math.pow

class LoyalAnalyticsWheel : WheelAnalyticsClass() {

    private fun generateDiscsInt(doubleArray: DoubleArray,discounts : List<Int>): MutableMap<Int,Int> {
        val (a, b) = listOf(doubleArray[0], doubleArray[1])
        return discounts.associateWith { (a * b.pow(-discounts.indexOf(it))).toInt() }.toMutableMap()
    }

    override fun makeComputation(tickets : Int,
                                 discounts : List<Int>,
                                 price : Int,
                                 budget : Int
    ): MutableMap<Int,Int>{
        val func = ArrayEval(budget,price,tickets,discounts)
        val arrStart = doubleArrayOf(tickets.toDouble()/2, 2.0)
        val res = BFGS.minimize(func, arrStart, 0.0001, 100000)

        val computationDiscounts = generateDiscsInt(arrStart,discounts)

        while(calculateCost(computationDiscounts,price) < budget && tickets >sum(computationDiscounts.values.map{it.toDouble()})){
            computationDiscounts.merge(discounts.minOf { it },1,Int::plus)
        }
        return computationDiscounts
    }
}