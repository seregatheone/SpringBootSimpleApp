package com.example.crocproject.data.services

import com.example.crocproject.data.models.missions.dto.MissionsDto
import com.example.crocproject.data.models.missions.repositorymodels.Mission
import com.example.crocproject.data.repository.MissionsRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class MissionsService(
    private val missionsRepository : MissionsRepository
) : MissionsInterface {
    override fun loadAllMissions(): List<MissionsDto> {
        return missionsRepository.loadAllMissions().map{it.toMissionDto()}
    }

    override fun storeMission(jsonData: String) {
        val missionsDto = MissionsDto(
            id = 0,
            jsonData = jsonData
        )
        missionsRepository.storeMission(missionsDto.toMission())
    }

    override fun loadOneMission(id: Int): MissionsDto {
        return when (val missionsData = missionsRepository.findMissionById(id)){
            null-> throw ResponseStatusException(HttpStatus.NOT_FOUND)
            else -> missionsData.toMissionDto()
        }
    }

    override fun deleteMissionById(id: Int) {
        missionsRepository.deleteMissionById(id)
    }

    private fun Mission.toMissionDto() =
        MissionsDto(
            id = id,
            jsonData = jsonData
        )
    private fun MissionsDto.toMission() =
        Mission(
            id = id,
            jsonData = jsonData
        )
}
interface MissionsInterface{
    fun loadAllMissions(): List<MissionsDto>
    fun storeMission(jsonData : String)
    fun loadOneMission(id : Int) : MissionsDto
    fun deleteMissionById(id : Int)
}