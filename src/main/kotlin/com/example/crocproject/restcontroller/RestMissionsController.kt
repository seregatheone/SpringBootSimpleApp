package com.example.crocproject.restcontroller

import com.example.crocproject.data.models.missions.MissionsDataClass
import com.example.crocproject.data.models.missions.dto.MissionsDto
import com.example.crocproject.data.services.MissionsService
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("missions")
class RestMissionsController(
    private val missionsService : MissionsService
){
    @GetMapping("/{missionId}")
    fun loadMissionById(@PathVariable missionId : Int) : MissionsDto{
        return missionsService.loadOneMission(missionId)
    }
    @DeleteMapping("/{missionId}")
    fun deleteMissionById(@PathVariable missionId : Int){
        missionsService.deleteMissionById(missionId)
    }
    @PostMapping
    fun storeMission(@RequestBody jsonData : String){
        missionsService.storeMission(jsonData)
    }
    @GetMapping
    fun loadAllMissions() : List<MissionsDto>{
        return missionsService.loadAllMissions()
    }
}