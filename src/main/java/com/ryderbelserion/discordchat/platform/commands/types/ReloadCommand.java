package com.ryderbelserion.discordchat.platform.commands.types;

import com.ryderbelserion.discordchat.platform.commands.BaseCommand;
import com.ryderbelserion.discordchat.platform.impl.enums.Messages;
import dev.triumphteam.cmd.bukkit.annotation.Permission;
import dev.triumphteam.cmd.core.annotations.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.PermissionDefault;

public class ReloadCommand extends BaseCommand {

    @Command
    @Permission(value = "discordchat.help", def = PermissionDefault.TRUE)
    public void help(CommandSender sender) {
        sender.sendRichMessage(Messages.player_help.getMessage(sender));
    }

    @Command("reload")
    @Permission(value = "discordchat.reload", def = PermissionDefault.OP)
    public void reload(CommandSender sender) {
        this.plugin.getConfigManager().reload();

        this.plugin.getDiscordBot().start();

        sender.sendRichMessage(Messages.reloaded_plugin.getMessage(sender));
    }
}