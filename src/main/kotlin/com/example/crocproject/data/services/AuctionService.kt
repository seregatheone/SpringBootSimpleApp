package com.example.crocproject.data.services

import com.example.crocproject.data.models.aucton.dto.AuctionDto
import com.example.crocproject.data.models.aucton.repositorymodels.Auction
import com.example.crocproject.data.repository.AuctionRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class AuctionService(
    private val auctionRepository : AuctionRepository
) : AuctionInterface {
    override fun loadAllAuctions(): List<AuctionDto> {
        return auctionRepository.loadAllAuctions().map{it.toAuctionDto()}
    }

    override fun storeAuction(auctionDto: AuctionDto) {
        auctionRepository.storeAuction(auctionDto.toAuction())
    }

    override fun loadOneAuction(id: Int): AuctionDto {
        return when (val auctionData = auctionRepository.findAuctionById(id)){
            null-> throw ResponseStatusException(HttpStatus.NOT_FOUND)
            else -> auctionData.toAuctionDto()
        }
    }

    override fun deleteAuctionById(id: Int) {
        auctionRepository.deleteAuctionById(id)
    }

    private fun Auction.toAuctionDto() =
        AuctionDto(
            id = id,
            jsonData = jsonData
        )
    private fun AuctionDto.toAuction() =
        Auction(
            id = id,
            jsonData = jsonData
        )
}
interface AuctionInterface{
    fun loadAllAuctions(): List<AuctionDto>
    fun storeAuction(auctionDto : AuctionDto)
    fun loadOneAuction(id : Int) : AuctionDto
    fun deleteAuctionById(id : Int)
}