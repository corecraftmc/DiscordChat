package com.ryderbelserion.discordchat.platform.commands.types;

import com.ryderbelserion.discordchat.platform.commands.BaseCommand;
import com.ryderbelserion.discordchat.platform.impl.Config;
import com.ryderbelserion.discordchat.platform.impl.Locale;
import dev.triumphteam.cmd.bukkit.annotation.Permission;
import dev.triumphteam.cmd.core.annotations.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.PermissionDefault;

public class ReloadCommand extends BaseCommand {

    @Command
    @Permission(value = "discordchat.help", def = PermissionDefault.TRUE)
    public void help(CommandSender sender) {
        this.locale.getProperty(Locale.help).forEach(sender::sendRichMessage);
    }

    @Command("reload")
    @Permission(value = "discordchat.reload", def = PermissionDefault.OP)
    public void reload(CommandSender sender) {
        // Reload the config files.
        this.plugin.getConfigManager().reload();

        // Start the bot.
        this.plugin.getDiscordBot().start();

        // Send the reload message.
        sender.sendRichMessage(this.locale.getProperty(Locale.reload_plugin).replaceAll("\\{prefix}", this.config.getProperty(Config.command_prefix)));
    }
}