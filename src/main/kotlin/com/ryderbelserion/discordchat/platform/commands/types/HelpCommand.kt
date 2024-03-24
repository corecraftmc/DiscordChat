package com.ryderbelserion.discordchat.platform.commands.types

import com.ryderbelserion.discordchat.platform.commands.BaseCommand
import com.ryderbelserion.discordchat.platform.impl.Locale
import dev.triumphteam.cmd.bukkit.annotation.Permission
import dev.triumphteam.cmd.core.annotations.Command
import org.bukkit.command.CommandSender
import org.bukkit.permissions.PermissionDefault

class HelpCommand : BaseCommand() {

    @Command
    @Permission(value = ["discordchat.help"], def = PermissionDefault.TRUE)
    fun help(sender: CommandSender) {
        this.locale.getProperty(Locale.help).forEach { line -> sender.sendRichMessage(line) }
    }
}