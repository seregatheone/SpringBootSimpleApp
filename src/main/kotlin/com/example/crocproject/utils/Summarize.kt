package com.example.crocproject.utils

fun calculateCost(map:Map<Int,Int>,price : Int):Int{
    var summa = 0
    map.forEach{ (percent, amount) ->
        summa += percent * amount * price / 100
    }
    return summa
}
fun sumOfIntList(listOfInts : List<Int>) : Int{
    return listOfInts.reduce(){summa, element -> summa + element}
}
fun <T,S> zipListsIntoMap(firstList : List<T>, secondList : List<S>) : Map<T,S>{
    val mapMerged = mutableMapOf<T,S>()
    for (i in 0 until maxOf(firstList.size, secondList.size)){
        mapMerged[firstList[i]] = secondList[i]
    }
    return mapMerged
}