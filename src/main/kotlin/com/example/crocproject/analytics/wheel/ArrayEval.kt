package com.example.crocproject.analytics.wheel

import jetbrains.datalore.base.math.ipow
import jetbrains.datalore.plot.common.data.SeriesUtil.sum
import smile.math.DifferentiableMultivariateFunction
import kotlin.math.pow

class ArrayEval(
    private val budget : Int,
    private val price : Int,
    private val tickets: Int,
    private val discounts: List<Int>
    ) : DifferentiableMultivariateFunction {
    override fun f(x: DoubleArray?): Double {
        val discountsComputation = generateDiscs(x!!, discounts = discounts)
        val deltaBudget = budget - calculateCost(discountsComputation,price)
        val lossB = deltaBudget.ipow(2)
        val deltaNumber = sum(discountsComputation.values.toList().map{it.toDouble()})-tickets
        val lossNumber = deltaNumber.pow(2)
        return WEIGHT_BUDGET*lossB + WEIGHT_NUMBER*lossNumber
    }

    override fun g(x: DoubleArray?, gradient: DoubleArray?): Double {
        val eps=1e-7
        val xda = DoubleArray(2){i -> x!![i] + eps*(1-i) }
        val xdb = DoubleArray(2){i -> x!![i] + eps*(i) }
        val dfda = (f(xda) - f(x))/eps
        val dfdb = (f(xdb) - f(x))/eps
        gradient?.set(0, dfda)
        gradient?.set(1, dfdb)
        return f(x)
    }

    private fun generateDiscs(doubleArray: DoubleArray,discounts : List<Int>): MutableMap<Int,Int> {
        val (a, b) = listOf(doubleArray[0], doubleArray[1])
        return discounts.associateWith { (a * b.pow(-it)).toInt() }.toMutableMap()
    }

    private fun calculateCost(map:Map<Int,Int>,price : Int):Int{
        var summa = 0
        map.forEach{ (percent, amount) ->
            summa+= percent * amount * price / 100
        }
        return summa
    }

    companion object {
        const val WEIGHT_BUDGET = 10
        const val WEIGHT_NUMBER = 5
    }
}