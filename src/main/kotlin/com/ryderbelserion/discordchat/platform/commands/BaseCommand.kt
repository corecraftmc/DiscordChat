package com.ryderbelserion.discordchat.platform.commands

import com.ryderbelserion.discordchat.DiscordChat
import com.ryderbelserion.discordchat.platform.ConfigManager
import dev.triumphteam.cmd.bukkit.annotation.Permission
import dev.triumphteam.cmd.core.annotations.Command
import org.bukkit.plugin.java.JavaPlugin

@Command("discordchat")
@Permission(value = ["discordchat.access"])
abstract class BaseCommand {

    protected val plugin = JavaPlugin.getPlugin(DiscordChat::class.java)

    protected val config = ConfigManager.config()

    protected val locale = ConfigManager.locale()
}