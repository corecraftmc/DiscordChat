package com.ryderbelserion.discordchat

import com.ryderbelserion.discordchat.discord.DiscordBot
import com.ryderbelserion.discordchat.platform.CommandManager
import com.ryderbelserion.discordchat.platform.ConfigManager
import com.ryderbelserion.discordchat.platform.impl.Config
import net.dv8tion.jda.api.entities.Guild
import org.bukkit.plugin.java.JavaPlugin

class DiscordChat : JavaPlugin() {

    private lateinit var bot: DiscordBot
    private lateinit var guild: Guild

    override fun onEnable() {
        ConfigManager.load()

        CommandManager.load()

        val token: String = ConfigManager.config().getProperty(Config.bot_token)

        if (token.isBlank()) {
            this.logger.severe("Token cannot be empty or blank.")

            return
        }

        this.bot = DiscordBot(this)
        this.bot.init()

        //this.guild = this.bot.get()?.getGuildById(ConfigManager.config().getProperty(Config.guild_id))!!
    }

    override fun onDisable() {
        this.bot.onStop()
        this.bot.disable()
    }

    //fun getGuild(): Guild = this.guild
}