package com.example.crocproject.restcontroller

import com.example.crocproject.data.models.wheel.ResponseWheel
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("missions")
class RestMissionsController{
    @GetMapping("/{wheelId}")
    fun loadMissionById(@PathVariable wheelId : Int){
    }
    @DeleteMapping("/{wheelId}")
    fun deleteMissionById(@PathVariable wheelId : Int){
    }
    @PostMapping
    fun storeMission(){
    }
    @GetMapping
    fun loadAllMissions(){
    }
}