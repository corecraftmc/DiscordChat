package com.ryderbelserion.discordchat.platform.commands.types

import com.ryderbelserion.discordchat.discord.DiscordBot
import com.ryderbelserion.discordchat.platform.ConfigManager
import com.ryderbelserion.discordchat.platform.commands.BaseCommand
import com.ryderbelserion.discordchat.platform.impl.Config
import com.ryderbelserion.discordchat.platform.impl.Locale
import dev.triumphteam.cmd.bukkit.annotation.Permission
import dev.triumphteam.cmd.core.annotations.Command
import org.bukkit.command.CommandSender
import org.bukkit.permissions.PermissionDefault

class ReloadCommand : BaseCommand() {

    @Command("reload")
    @Permission(value = ["discordchat.reload"], def = PermissionDefault.OP)
    fun reload(sender: CommandSender) {
        ConfigManager.reload()

        val token: String = ConfigManager.config().getProperty(Config.bot_token)

        if (token.isBlank()) {
            this.plugin.logger.severe("Token cannot be empty or blank.")
        } else {
            val bot = DiscordBot(this.plugin)

            if (bot.get() != null) {
                this.plugin.logger.warning("The bot is already enabled.")
            } else {
                bot.init()
            }
        }

        sender.sendRichMessage(this.locale.getProperty(Locale.reload_plugin).replace("\\{prefix}".toRegex(), this.config.getProperty(Config.command_prefix)))
    }
}