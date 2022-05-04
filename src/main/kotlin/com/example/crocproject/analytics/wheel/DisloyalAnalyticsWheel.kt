package com.example.crocproject.analytics.wheel

import com.example.crocproject.data.models.wheel.WheelGoodsRequestBody
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import java.lang.Integer.sum
import kotlin.random.Random
import kotlin.system.measureTimeMillis

//import time
//from random import randint
//
//# цена лота
//price = 500
//# бюджет
//budget = 5000
//# число билетов
//tickets = 50
//
//# виды скидок
//# i*5% - скидка, т.е 1 - 5%, 2 - 10%, 10 - 50% ...
//disc = {i: tickets // 16 for i in [i for i in range(1, 17)]}
//
//
//def calc_spent(disc):
//    return sum([disc[i] * i * price / 20 for i in disc])
//
//
//# у нас 5 секунд на выбор
//t = time.time()
//while time.time() - t < 7:
//    spent = calc_spent(disc)
//    # перекинем 1 случайный билет в категорию выше или ниже
//    # ниже
//    if spent > budget:
//        cat = randint(2, 16)
//        if disc[cat] <= 0:
//            continue
//        disc[cat] -= 1
//        disc[cat - 1] += 1
//    # выше
//    else:
//        cat = randint(1, 15)
//        if disc[cat] <= 0:
//            continue
//        disc[cat] -= 1
//        disc[cat + 1] += 1
//
//
//for i in disc:
//    print(f'{round(i * 0.05, 1)* 100}%', disc[i])

class DisloyalAnalyticsWheel(private val wheelGoodsRequestBody : WheelGoodsRequestBody) {
    fun countTickets() {
        for (fortuneItem in wheelGoodsRequestBody.fortuneItems){
            val tickets = fortuneItem.tickets
            val discounts = MutableList(16) { tickets / 16 }
            val price = fortuneItem.price
            val budget = fortuneItem.budget
            val scope = CoroutineScope(Dispatchers.Default)
            val time = scope.launch {
                withTimeout(2000) {
                    while (true) {
                        val spent = calculateCost(discounts, price)
                        if (spent > budget) {
                            val cat = Random.nextInt(2, 17)
                            if (discounts[cat] <= 0) {
                                continue
                            }
                            discounts[cat] -= 1
                            discounts[cat - 1] += 1

                        }
                    }
                }
            }
        }
    }
    private fun calculateCost(list:List<Int>,price : Int):Int{
        var summa = 0
        list.forEachIndexed { index, i ->
            summa+= i * index * price / 20
        }
        return summa
    }
}