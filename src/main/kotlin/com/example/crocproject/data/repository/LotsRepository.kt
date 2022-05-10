package com.example.crocproject.data.repository

import com.example.crocproject.data.models.wheel.repositorymodels.Lot
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

interface LotsRepository{
    fun loadAllLots(): List<Lot>
    fun storeLot(lot : Lot)
    fun findLotById(id : Int) : Lot?
    fun deleteLotById(id : Int)
}


@Repository
class LotsRepositoryImpl(
    private val jdbcTemplate: NamedParameterJdbcTemplate
):LotsRepository{
    override fun loadAllLots(): List<Lot> {
        return jdbcTemplate.query(
            "SELECT * FROM lots",
            ROW_MAPPER
        )
    }

    override fun storeLot(lot: Lot) {
        jdbcTemplate.update (
            "INSERT INTO lots (json_data) VALUES (:jsonData)",
            mapOf(
                "jsonData" to lot.jsonData
            )
        )

    }

    override fun findLotById(id: Int): Lot? {
        return jdbcTemplate.query(
            "SELECT * FROM lots WHERE lot_id = :id",
            mapOf(
                "id" to id
            ),
            ROW_MAPPER
        ).firstOrNull()
    }

    override fun deleteLotById(id: Int) {
        jdbcTemplate.update(
            "DELETE FROM lots WHERE lot_id = :id",
            mapOf(
                "id" to id
            )
        )
    }

    private companion object{
        val ROW_MAPPER = RowMapper<Lot>{
                rs,_ ->
            Lot(
                id = rs.getInt("lot_id"),
                jsonData = rs.getString("json_data")
            )
        }
    }

}