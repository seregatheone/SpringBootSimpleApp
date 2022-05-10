package com.example.crocproject.data.repository

import com.example.crocproject.data.models.missions.repositorymodels.Mission
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

interface MissionsRepository{
    fun loadAllMissions(): List<Mission>
    fun storeMission(mission : Mission)
    fun findMissionById(id : Int) : Mission?
    fun deleteMissionById(id : Int)
}


@Repository
class MissionsRepositoryImpl(
    private val jdbcTemplate: NamedParameterJdbcTemplate
):MissionsRepository{
    override fun loadAllMissions(): List<Mission> {
        return jdbcTemplate.query(
            "SELECT * FROM missions",
            ROW_MAPPER
        )
    }

    override fun storeMission(mission: Mission) {
        jdbcTemplate.update (
            "insert into missions (json_data) values (:jsonData)",
            mapOf(
                "jsonData" to mission.jsonData,
            )
        )

    }

    override fun findMissionById(id: Int): Mission? {
        return jdbcTemplate.query(
            "SELECT * FROM missions WHERE mission_id = :id",
            mapOf(
                "id" to id
            ),
            ROW_MAPPER
        ).firstOrNull()
    }

    override fun deleteMissionById(id: Int) {
        jdbcTemplate.update(
            "DELETE FROM missions WHERE mission_id = :id",
            mapOf(
                "id" to id
            )
        )
    }

    private companion object{
        val ROW_MAPPER = RowMapper<Mission>{
                rs,_ ->
            Mission(
                id = rs.getInt("mission_id"),
                jsonData = rs.getString("json_data")
            )
        }
    }

}