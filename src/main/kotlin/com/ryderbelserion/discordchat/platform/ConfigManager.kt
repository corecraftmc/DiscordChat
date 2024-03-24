package com.ryderbelserion.discordchat.platform

import ch.jalu.configme.SettingsManager
import ch.jalu.configme.SettingsManagerBuilder
import ch.jalu.configme.resource.YamlFileResourceOptions
import com.ryderbelserion.discordchat.DiscordChat
import com.ryderbelserion.discordchat.platform.impl.Config
import com.ryderbelserion.discordchat.platform.impl.Locale
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

object ConfigManager {

    private val plugin = JavaPlugin.getPlugin(DiscordChat::class.java)

    private lateinit var config: SettingsManager
    private lateinit var locale: SettingsManager

    fun load() {
        val builder = YamlFileResourceOptions.builder().indentationSize(2).build()

        this.config = SettingsManagerBuilder
            .withYamlFile(File(this.plugin.dataFolder, "config.yml"), builder)
            .useDefaultMigrationService()
            .configurationData(Config::class.java)
            .create()

        this.locale = SettingsManagerBuilder
            .withYamlFile(File(this.plugin.dataFolder, "messages.yml"), builder)
            .useDefaultMigrationService()
            .configurationData(Locale::class.java)
            .create()
    }

    fun reload() {
        config.reload()

        locale.reload()
    }

    fun config(): SettingsManager = config

    fun locale(): SettingsManager = locale
}