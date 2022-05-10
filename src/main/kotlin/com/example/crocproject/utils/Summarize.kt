package com.example.crocproject.utils

fun calculateCost(map:Map<Int,Int>,price : Int):Int{
    var summa = 0
    map.forEach{ (percent, amount) ->
        summa += percent * amount * price / 100
    }
    return summa
}
fun sumOfInts(listOfInts : List<Int>) : Int{
    return listOfInts.reduce(){summa, element -> summa + element}
}