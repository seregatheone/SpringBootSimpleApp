package com.example.crocproject.analytics.missions

import com.example.crocproject.data.models.missions.restmodels.request.RequestMission
import com.example.crocproject.data.models.missions.restmodels.response.*
import com.example.crocproject.utils.sumOfIntList
import com.example.crocproject.utils.zipListsIntoMap
import jetbrains.datalore.plot.common.data.SeriesUtil.sum

class MissionsAnalytics(private val requestMission: RequestMission) {
    fun countTickets(): ResponseMission {
        val missionItemsList = mutableListOf<MissionResponseItem>()
        var totalSpent = 0.0
        var totalTicketsAtEnd = 0.0

        requestMission.missions.forEach { mission ->
            val busters = mission.multBusters.map { it.toDouble() }
                .associateBy { "multBusters" } + mission.percentBusters.map { it.toDouble() / 100.0 }
                .associateBy { "percentBusters" } + mission.fixed.map { it.toDouble() / mission.missionIncome }
                .associateBy { "fixed" }
            val allBusters = busters.toSortedMap()
            val missionIncome = mission.missionIncome
            val missionBudget = mission.budget
            var spent = 0.0
            var disc = zipListsIntoMap(allBusters.toList(), List(allBusters.size) { 0.0 })
            while (spent < missionBudget) {
                val prevDisc = disc
                val bustersRatio = disc.keys.toMutableList()
                val ticketCount = disc.values.toMutableList()
                for (i in bustersRatio.indices) {
                    if (spent + bustersRatio[i].second * missionIncome <= missionBudget) {
                        ticketCount[i] += 1.0
                        spent = missionSpent(
                            map = zipListsIntoMap(bustersRatio.map { it.second }, ticketCount),
                            missionReward = missionIncome
                        )
                    }
                }
                disc = zipListsIntoMap(bustersRatio, ticketCount)
                if (prevDisc == disc) {
                    break
                }
            }
            totalSpent += spent

            val res = zipListsIntoMap(disc.keys.toList(), disc.values.toList())
            totalTicketsAtEnd += sum(res.values.toList())

            val fixedBusters = mutableListOf<FixedBusters>()
            res.filter { it.key.first == "fixed" }.forEach {
                fixedBusters.add(
                    FixedBusters(
                        bonus = it.key.second.toInt(),
                        tickets = it.value.toInt()
                    )
                )

            }

            val multBusters = mutableListOf<MultBusters>()
            res.filter { it.key.first == "multBusters" }.forEach {
                multBusters.add(
                    MultBusters(
                        bonus = it.key.second.toInt(),
                        tickets = it.value.toInt()
                    )
                )

            }

            val percentBusters = mutableListOf<PercentBusters>()
            res.filter { it.key.first == "percentBusters" }.forEach {
                percentBusters.add(
                    PercentBusters(
                        bonus = it.key.second.toInt(),
                        tickets = it.value.toInt()
                    )
                )

            }

            missionItemsList.add(
                MissionResponseItem(
                    spent = spent.toInt(),
                    budget = mission.budget,
                    fixedTickets = res.keys.toList().filter { it.first == "fixed" }.size,
                    multBusterTickets = res.keys.toList().filter { it.first == "multBusters" }.size,
                    percentBusterTickets = res.keys.toList().filter { it.first == "percentBusters" }.size,
                    fixedBusters = fixedBusters,
                    missionName = mission.missionName,
                    tickets = res.size,
                    percentBusters = percentBusters,
                    multBusters = multBusters,
                    missionIncome = missionIncome,
                )
            )
        }
        return ResponseMission(
            totalSpent = totalSpent.toInt(),
            totalTickets = totalTicketsAtEnd.toInt(),
            missions = missionItemsList.toList()
        )
    }

    private fun missionSpent(map: Map<Double, Double>, missionReward: Int): Double {
        val listOfMissions = mutableListOf<Double>()
        map.forEach {
            listOfMissions.add(it.value * it.key * missionReward)
        }
        return sum(listOfMissions)
    }
}