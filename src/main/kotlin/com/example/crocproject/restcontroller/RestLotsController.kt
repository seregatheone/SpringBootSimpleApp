package com.example.crocproject.restcontroller

import com.example.crocproject.data.models.wheel.dto.LotsDto
import com.example.crocproject.data.services.LotsService
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("wheel-lots")
class RestLotsController(
    private val lotsService : LotsService
){
    @GetMapping("/{wheelId}")
    fun loadWheelById(@PathVariable wheelId : Int) : LotsDto{
        return lotsService.loadOneLot(wheelId)
    }
    @DeleteMapping("/{wheelId}")
    fun deleteWheelById(@PathVariable wheelId : Int){
        lotsService.deleteLotById(wheelId)
    }
    @PostMapping
    fun storeLot(@RequestBody jsonData : String){
        lotsService.storeLot(jsonData)
    }
    @GetMapping
    fun loadAllLots() : List<LotsDto>{
        return lotsService.loadAllLots()
    }

}