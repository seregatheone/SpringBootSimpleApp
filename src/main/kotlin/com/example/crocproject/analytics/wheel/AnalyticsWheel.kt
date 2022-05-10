package com.example.crocproject.analytics.wheel

import com.example.crocproject.data.models.wheel.restmodels.FortuneItem
import com.example.crocproject.data.models.wheel.restmodels.ResponseWheel
import com.example.crocproject.data.models.wheel.restmodels.WheelGoodsRequestBody
import com.example.crocproject.utils.calculateCost
import jetbrains.datalore.plot.common.data.SeriesUtil.sum
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException


class AnalyticsWheel(private val wheelGoodsRequestBody : WheelGoodsRequestBody) {

    private val analyticsClass : WheelAnalyticsClass = when(wheelGoodsRequestBody.computationType){
        0 -> {
            DisloyalAnalyticsWheel()
        }
        1 -> {
            LoyalAnalyticsWheel()
        }
        else -> throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun countTickets() : ResponseWheel {
        val fortuneItemsList = mutableListOf<FortuneItem>()
        var totalSpent = 0
        var totalTickets = 0
        val totalComputationBudget = wheelGoodsRequestBody.computationBudget
        for (fortuneItem in wheelGoodsRequestBody.fortuneItems){
            val tickets = fortuneItem.tickets
            val discounts = fortuneItem.discounts.toMutableList()
            val price = fortuneItem.price
            val percentage = fortuneItem.percentage
            val discountTickets = mutableListOf<Pair<Int, Int>>()

            val result = analyticsClass.makeComputation(
                    tickets = tickets,
                    discounts = discounts,
                    price = price,
                    budget = percentage * totalComputationBudget/100,
                )
            result.forEach{ (discount, amount) ->
                discountTickets.add(
                    Pair(discount, amount)
                )
            }
            val spent = calculateCost(result,price)

            val currentTickets = sum(result.values.toList().map{it.toDouble()}).toInt()

            totalSpent += spent
            totalTickets += currentTickets

            val fortuneItemsListElement = FortuneItem(
                id = fortuneItem.id,
                lotName = fortuneItem.lotName,
                discountTickets = discountTickets,
                totalSpentInRub = spent,
                tickets = currentTickets
            )

            fortuneItemsList.add(fortuneItemsListElement)
        }

        return ResponseWheel(
            spent = totalSpent,
            tickets = totalTickets,
            fortuneItems = fortuneItemsList
        )
    }

}