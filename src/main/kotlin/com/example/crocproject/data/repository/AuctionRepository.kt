package com.example.crocproject.data.repository

import com.example.crocproject.data.models.aucton.repositorymodels.Auction
import com.example.crocproject.data.models.aucton.repositorymodels.Settings
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

interface AuctionRepository{
    fun loadAllAuctions(): List<Auction>
    fun storeAuction(auction : Auction)
    fun findAuctionById(id : Int) : Auction?
    fun deleteAuctionById(id : Int)
}


@Repository
class AuctionRepositoryImpl(
    private val jdbcTemplate: NamedParameterJdbcTemplate
):AuctionRepository{
    override fun loadAllAuctions(): List<Auction> {
        return jdbcTemplate.query(
            "SELECT * FROM auctions",
            ROW_MAPPER
        )
    }

    override fun storeAuction(auction: Auction) {
        jdbcTemplate.update (
            "INSERT INTO auctions (auction) VALUES (:auction)",
            mapOf(
                "auction" to auction.jsonData
            )
        )

    }

    override fun findAuctionById(id: Int): Auction? {
        return jdbcTemplate.query(
            "SELECT * FROM auctions WHERE auction_id = :id",
            mapOf(
                "id" to id
            ),
            ROW_MAPPER
        ).firstOrNull()
    }

    override fun deleteAuctionById(id: Int) {
        jdbcTemplate.update(
            "DELETE FROM auctions WHERE auction_id = :id",
            mapOf(
                "id" to id
            )
        )
    }

    private companion object{
        val ROW_MAPPER = RowMapper<Auction>{
                rs,_ ->
            Auction(
                id = rs.getInt("auction_id"),
                jsonData = rs.getString("auction")
            )
        }
    }

}