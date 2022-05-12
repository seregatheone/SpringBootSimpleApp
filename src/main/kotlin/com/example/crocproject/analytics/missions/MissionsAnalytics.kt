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
            val busters = mission.multBusters.map { it.toDouble() }.associateWith { "multBusters" }.toList() +
                    mission.percentBusters.map { it.toDouble() / 100.0 }.associateWith { "percentBusters" }.toList() +
                    mission.fixed.map { it.toDouble() / mission.missionIncome }.associateWith { "fixed" }.toList()
            val allBusters = busters.sortedBy { it.first }
            val missionIncome = mission.missionIncome
            val missionBudget = mission.budget
            var spent = 0.0
            var disc = zipListsIntoMap(allBusters, List(allBusters.size) { 0.0 })
            while (spent < missionBudget) {
                val prevDisc = disc
                val bustersRatio = disc.keys.toMutableList()
                val ticketCount = disc.values.toMutableList()
                for (i in bustersRatio.indices) {
                    if (spent + bustersRatio[i].first * missionIncome <= missionBudget) {
                        ticketCount[i] += 1.0
                        spent = missionSpent(
                            map = zipListsIntoMap(bustersRatio.map { it.first }, ticketCount),
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
            res.filter { it.key.second == "fixed" }.forEach {
                fixedBusters.add(
                    FixedBusters(
                        bonus = (it.key.first * missionIncome).toInt(),
                        tickets = it.value.toInt()
                    )
                )

            }

            val multBusters = mutableListOf<MultBusters>()
            res.filter { it.key.second == "multBusters" }.forEach {
                multBusters.add(
                    MultBusters(
                        bonus = (it.key.first).toInt(),
                        tickets = it.value.toInt()
                    )
                )

            }

            val percentBusters = mutableListOf<PercentBusters>()
            res.filter { it.key.second == "percentBusters" }.forEach {
                percentBusters.add(
                    PercentBusters(
                        bonus = (it.key.first * 100).toInt(),
                        tickets = it.value.toInt()
                    )
                )

            }

            missionItemsList.add(
                MissionResponseItem(
                    spent = spent.toInt(),
                    budget = mission.budget,
                    fixedTickets = res.keys.toList().filter { it.second == "fixed" }.size,
                    multBusterTickets = res.keys.toList().filter { it.second == "multBusters" }.size,
                    percentBusterTickets = res.keys.toList().filter { it.second == "percentBusters" }.size,
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
