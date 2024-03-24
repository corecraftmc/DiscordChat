package com.ryderbelserion.discordchat.discord

import ch.jalu.configme.SettingsManager
import com.ryderbelserion.discordchat.DiscordChat
import com.ryderbelserion.discordchat.discord.api.DedicatedModule
import com.ryderbelserion.discordchat.platform.ConfigManager
import com.ryderbelserion.discordchat.platform.impl.Config
import com.ryderbelserion.discordchat.platform.impl.Locale
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel
import net.dv8tion.jda.api.requests.GatewayIntent
import java.io.File

class DiscordBot(private val plugin: DiscordChat) : DedicatedModule(
    listOf(
        GatewayIntent.GUILD_MESSAGES
    ),

    listOf(

    ),

    File(plugin.dataFolder, "bot")
) {

    private val config: SettingsManager = ConfigManager.config()

    private val locale: SettingsManager = ConfigManager.locale()

    override fun onStart() {
        this.plugin.logger.info("Starting the bot.")
    }

    override fun onReady() {

    }

    override fun onStop() {
        val message = this.locale.getProperty(Locale.server_shutdown)

        if (message.isEmpty() || message.isBlank()) return

        val guild: Guild? = get()?.getGuildById(this.config.getProperty(Config.guild_id))

        for (id: String in this.config.getProperty(Config.channels)) {
            val channel: TextChannel = guild?.getTextChannelById(id) ?: continue

            channel.sendMessage(message).queue()
        }
    }

    override fun token(): String {
        return this.config.getProperty(Config.bot_token)
    }

    override fun onGuildReady(guild: Guild) {
        if (!this.config.getProperty(Config.guild_id).equals(guild.id, true)) {
            this.config.setProperty(Config.guild_id, guild.id)

            this.config.save()
            this.config.reload()
        }

        val message = this.locale.getProperty(Locale.server_started)

        if (message.isEmpty() || message.isBlank()) return

        for (id: String in this.config.getProperty(Config.channels)) {
            val channel: TextChannel = guild.getTextChannelById(id) ?: continue

            channel.sendMessage(message).queue()
        }
    }
}