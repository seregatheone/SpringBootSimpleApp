package com.example.crocproject.restcontroller

import com.example.crocproject.data.models.wheel.ResponseWheel
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("wheel-lots")
class RestLotsController{
    @GetMapping("/{wheelId}")
    fun loadWheelById(@PathVariable wheelId : Int){
    }
    @DeleteMapping("/{wheelId}")
    fun deleteWheelById(@PathVariable wheelId : Int){
    }
    @PostMapping
    fun storeLot(){

    }
    @GetMapping
    fun loadAllLots(){

    }

}