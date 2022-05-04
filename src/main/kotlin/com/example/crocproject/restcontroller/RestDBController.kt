package com.example.crocproject.restcontroller

import com.example.crocproject.data.models.aucton.dto.AuctionDto
import com.example.crocproject.data.models.aucton.dto.SettingsDto
import com.example.crocproject.data.services.AuctionService
import com.example.crocproject.data.services.SettingsService
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
class RestDBSettingsController(
    private val settingsService : SettingsService
){
    @PostMapping("/settings")
    fun storeSettings(@RequestBody json : String){
        settingsService.storeSettings(
            SettingsDto(
                id = 0,
                jsonData = json
            )
        )
    }

    @GetMapping("/settings")
    fun loadSettings() : SettingsDto {
        return settingsService.loadSettings()
    }
}
@CrossOrigin
@RestController
class RestDBAuctionsController(
    private val auctionService : AuctionService
){
    @PostMapping("/auction")
    fun storeAuction(@RequestBody json : String){
        auctionService.storeAuction(
            AuctionDto(
                id = 0,
                jsonData = json
            )
        )
    }

    @GetMapping("/auction/{auctionId}")
    fun loadOneAuction(@PathVariable auctionId : Int) : AuctionDto {
        return auctionService.loadOneAuction(auctionId)
    }

    @GetMapping("/auctions")
    fun loadAllAuctions() : List<AuctionDto>{
        return auctionService.loadAllAuctions()
    }

    @DeleteMapping("/auction/{auctionId}")
    fun deleteAuction(@PathVariable auctionId : Int){
        auctionService.deleteAuctionById(auctionId)
    }
}