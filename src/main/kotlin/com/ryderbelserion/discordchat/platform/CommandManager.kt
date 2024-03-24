package com.ryderbelserion.discordchat.platform

import com.ryderbelserion.discordchat.DiscordChat
import com.ryderbelserion.discordchat.platform.commands.types.HelpCommand
import com.ryderbelserion.discordchat.platform.commands.types.ReloadCommand
import dev.triumphteam.cmd.bukkit.BukkitCommandManager
import org.bukkit.command.CommandSender
import org.bukkit.plugin.java.JavaPlugin

object CommandManager {

    private val plugin = JavaPlugin.getPlugin(DiscordChat::class.java)

    private val commandManager: BukkitCommandManager<CommandSender> = BukkitCommandManager.create(this.plugin)

    fun load() {
        listOf(
            HelpCommand(),
            ReloadCommand()
        ).forEach { command -> this.commandManager.registerCommand(command) }
    }

    fun getCommandManager() = this.commandManager
}