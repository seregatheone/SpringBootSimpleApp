package com.example.crocproject.analytics.wheel

sealed class WheelAnalyticsClass {
    abstract fun makeComputation(tickets : Int,
                                 discounts : List<Int>,
                                 price : Int,
                                 budget : Int
    ): MutableMap<Int,Int>
}