package com.example.crocproject.data.repository

import com.example.crocproject.data.models.aucton.repositorymodels.Auction
import com.example.crocproject.data.models.aucton.repositorymodels.Settings
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

interface SettingsRepository{
    fun loadSettings(): Settings?
    fun storeSettings(settings : Settings)
}

@Repository
class SettingsRepositoryImpl(
    private val jdbcTemplate: NamedParameterJdbcTemplate
):SettingsRepository{
    override fun loadSettings(): Settings? {
        return jdbcTemplate.query(
            "SELECT * FROM settings_table",
            ROW_MAPPER
        ).firstOrNull()
}

    override fun storeSettings(settings: Settings) {
        when(val response = jdbcTemplate.query(
            "SELECT * FROM settings_table",
            ROW_MAPPER
        ).size){
            0-> {
                print(response)
                jdbcTemplate.update (
                        "INSERT INTO settings_table (settings) VALUES (:settings)",
                mapOf(
                    "settings" to settings.jsonData
                )
                )
            }
            else -> {
                jdbcTemplate.update(
                    "UPDATE settings_table SET settings = :settings",
                    mapOf(
                        "settings" to settings.jsonData
                    )
                )
                print(response)
            }
        }
    }

    private companion object{
        val ROW_MAPPER = RowMapper<Settings>{
            rs,_ ->
                Settings(
                    id = rs.getInt("settings_id"),
                    jsonData = rs.getString("settings")
                )
        }
    }

}