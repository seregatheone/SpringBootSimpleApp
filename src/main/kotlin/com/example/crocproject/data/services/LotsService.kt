package com.example.crocproject.data.services

import com.example.crocproject.data.models.wheel.dto.LotsDto
import com.example.crocproject.data.models.wheel.repositorymodels.Lot
import com.example.crocproject.data.repository.LotsRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class LotsService(
    private val lotsRepository : LotsRepository
) : LotsInterface {
    override fun loadAllLots(): List<LotsDto> {
        return lotsRepository.loadAllLots().map{it.toLotDto()}
    }

    override fun storeLot(jsonData: String) {
        val lotsDto = LotsDto(
            id = 0,
            jsonData = jsonData
        )
        lotsRepository.storeLot(lotsDto.toLot())
    }

    override fun loadOneLot(id: Int): LotsDto {
        return when (val lotsData = lotsRepository.findLotById(id)){
            null-> throw ResponseStatusException(HttpStatus.NOT_FOUND)
            else -> lotsData.toLotDto()
        }
    }

    override fun deleteLotById(id: Int) {
        lotsRepository.deleteLotById(id)
    }

    private fun Lot.toLotDto() =
        LotsDto(
            id = id,
            jsonData = jsonData
        )
    private fun LotsDto.toLot() =
        Lot(
            id = id,
            jsonData = jsonData
        )
}
interface LotsInterface{
    fun loadAllLots(): List<LotsDto>
    fun storeLot(jsonData : String)
    fun loadOneLot(id : Int) : LotsDto
    fun deleteLotById(id : Int)
}