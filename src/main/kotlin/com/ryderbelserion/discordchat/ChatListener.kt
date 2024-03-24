package com.ryderbelserion.discordchat

import ch.jalu.configme.SettingsManager
import com.ryderbelserion.discordchat.platform.ConfigManager
import com.ryderbelserion.discordchat.platform.impl.Config
import io.papermc.paper.event.player.AsyncChatEvent
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

class ChatListener : Listener {

    private val plugin = JavaPlugin.getPlugin(DiscordChat::class.java)

    private val config: SettingsManager = ConfigManager.config()

    //private val guild: Guild = this.plugin.getGuild()

    @EventHandler
    fun onPlayerChat(event: AsyncChatEvent) {
        val player = event.player

        val message = event.signedMessage().message()

        for (id in this.config.getProperty(Config.channels)) {
            //val channel = this.guild.getTextChannelById(id) ?: continue

            //val format = "{name} > {message}"

            //channel.sendMessage(
            //    format.replace("\\{name}".toRegex(), player.name).replace("\\{message}".toRegex(), message)
            //).queue()
        }
    }
}