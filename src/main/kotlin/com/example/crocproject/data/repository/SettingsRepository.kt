package com.example.crocproject.data.repository

import com.example.crocproject.data.models.repositorymodels.Auction
import com.example.crocproject.data.models.repositorymodels.Settings
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
            "select * from settings_table WHERE settings_id = 0",
            ROW_MAPPER
        ).firstOrNull()
}

    override fun storeSettings(settings: Settings) {
        when(jdbcTemplate.query(
            "select * from settings_table",
            ROW_MAPPER
        ).size){
            0-> {
                jdbcTemplate.update (
                        "insert into settings_table (settings_id, settings) values (:settings_id, :settings)",
                mapOf(
                    "settings_id" to settings.id,
                    "settings" to settings.jsonData
                )
                )
            }
            else -> jdbcTemplate.update(
                "UPDATE settings_table SET settings = :settings WHERE settings_id = :settings_id",
                mapOf(
                    "settings_id" to settings.id,
                    "settings" to settings.jsonData
                )
            )
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