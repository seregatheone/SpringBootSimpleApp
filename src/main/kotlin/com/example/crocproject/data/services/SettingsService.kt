package com.example.crocproject.data.services

import com.example.crocproject.data.models.dto.SettingsDto
import com.example.crocproject.data.models.repositorymodels.Settings
import com.example.crocproject.data.repository.SettingsRepository
import jdk.jshell.execution.Util
import org.apache.commons.io.IOUtils
import org.springframework.core.io.ClassPathResource
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.io.IOException
import java.nio.charset.Charset
import kotlin.text.Charsets.UTF_8

@Service
class SettingsServiceImpl(
    private val settingsRepository : SettingsRepository
) : SettingsService{
    override fun loadSettings(): SettingsDto {
        return when (val settingsData = settingsRepository.loadSettings()){
            null-> throw ResponseStatusException(HttpStatus.NOT_FOUND)
            else -> settingsData.toSettingsDto()
        }
    }

    override fun storeSettings(settingsDto : SettingsDto) {
        settingsRepository.storeSettings(settingsDto.toSettings())
    }
    private fun Settings.toSettingsDto() =
        SettingsDto(
            id = id,
            jsonData = jsonData
        )
    private fun SettingsDto.toSettings() =
        Settings(
            id = id,
            jsonData = jsonData
        )
}
interface SettingsService{
    fun loadSettings(): SettingsDto
    fun storeSettings(settingsDto : SettingsDto)
}