package com.example.crocproject.analytics.wheel

import com.example.crocproject.data.models.wheel.FortuneItem
import com.example.crocproject.data.models.wheel.ResponseWheel
import com.example.crocproject.data.models.wheel.WheelGoodsRequestBody
import kotlinx.coroutines.*
import kotlin.random.Random

class DisloyalAnalyticsWheel(private val wheelGoodsRequestBody : WheelGoodsRequestBody) {
    fun countTickets() : ResponseWheel {
        val fortuneItemsList = mutableListOf<FortuneItem>()
        var spent = 0
        for (fortuneItem in wheelGoodsRequestBody.fortuneItems){
            val tickets = fortuneItem.tickets.toInt()
            val discounts = MutableList(16) { tickets / 16 }
            discounts[15]+=tickets%16
            val price = fortuneItem.price.toInt()
            val budget = fortuneItem.budget.toInt()
            val discountTickets = mutableListOf<Pair<String, Int>>()

            CoroutineScope(Dispatchers.Default).launch {
                val resultDeferred : Deferred<List<Int>> = async{
                    makeComputation(
                        tickets = tickets,
                        discounts = discounts,
                        price = price,
                        budget = budget
                    )
                }
                val result = resultDeferred.await()
                result.forEachIndexed { index, i ->
                    discountTickets.add(
                            Pair("${index*5}", i)
                        )
                    }
                spent += calculateCost(discounts,price)
            }
            val fortuneItemsListElement = FortuneItem(
                id = fortuneItem.id,
                lotName = fortuneItem.lotName,
                discountTickets = discountTickets
            )

            fortuneItemsList.add(fortuneItemsListElement)
        }

        return ResponseWheel(
            spent,
            fortuneItems = fortuneItemsList
        )
    }
    private fun calculateCost(list:List<Int>,price : Int):Int{
        var summa = 0
        list.forEachIndexed { index, i ->
            summa+= i * index * price / 20
        }
        return summa
    }
    private fun makeComputation(
        tickets : Int,
        discounts : MutableList<Int>,
        price : Int,
        budget : Int
    ) : List<Int>{
        for (i in 0..tickets*100) {
            val spent = calculateCost(discounts, price)
            if (spent > budget) {
                val cat = Random.nextInt(1, 16)
                if (discounts[cat] <= 0) {
                    continue
                }
                discounts[cat] -= 1
                discounts[cat - 1] += 1

            }
        }
        return discounts
    }
}
