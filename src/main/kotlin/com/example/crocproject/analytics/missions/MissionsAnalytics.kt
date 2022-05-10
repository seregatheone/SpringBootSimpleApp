package com.example.crocproject.analytics.missions

import com.example.crocproject.data.models.missions.restmodels.request.Mission
import com.example.crocproject.data.models.missions.restmodels.request.RequestMission
import com.example.crocproject.data.models.missions.restmodels.response.ResponseMission
import com.example.crocproject.utils.sumOfInts
import jetbrains.datalore.plot.common.data.SeriesUtil.sum

//class MissionsAnalytics(private val requestMission : RequestMission) {
//    fun countTickets() : ResponseMission {
//        val missionItemsList = mutableListOf<Mission>()
//        var totalSpent = 0
//        val totalTicketsAtStart = requestMission.totalTickets
//        var totalTicketsAtEnd = 0
//
//        requestMission.missions.forEach{ mission ->
//            val busters = mission.multBusters + mission.percentBusters.map{it/100} + mission.fixed.map{it/mission.missionIncome}
//            val allBusters = busters.sorted()
//            val missionIncome = mission.missionIncome
//            val missionBudget = mission.budget
//            var spent = 0
//            val minBuster = allBusters[0]
//            val disc = busters.associateWith { 0 }
//            while(spent < mission.budget){
//                val prevDisc = disc
//                val mutableKeys = disc.keys.toMutableList()
//                val mutableValue = disc.values.toMutableList()
//                val (bustersRatio, ticketCount) = listOf(mutableKeys,mutableValue)
//                for ( i in bustersRatio.indices){
//                    if (spent + bustersRatio[i] * missionIncome <= missionBudget){
//                        ticketCount[i]+=1
//                        spent = missionSpent(bustersRatio.associateTo(ticketCount) { it to  })
//                    }
//                }
//                if spent + (bustersRatio[i] * missionEarnings) <= missionBudget:
//                ticketCount[i] += 1
//                spent = mission_spent(form_disc(bustersRatio, ticketCount))
//                disc = form_disc(bustersRatio, ticketCount)
//                if disc == prev_disc:
//                break
//            }
//        }
//    }
//
//    private fun missionSpent(map : Map<Int,Int>) : Int{
//        val listOfMissions = mutableListOf<Int>()
//        map.forEach {
//            listOfMissions.add (it.value * it.key)
//        }
//        return sumOfInts(listOfMissions)
//    }
//    private val
//}