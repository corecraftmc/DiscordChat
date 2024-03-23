package com.ryderbelserion.discordchat.platform;

import ch.jalu.configme.SettingsManager;
import com.ryderbelserion.discordchat.DiscordChat;
import com.ryderbelserion.discordchat.platform.impl.Config;
import com.ryderbelserion.discordchat.platform.impl.Locale;
import dev.triumphteam.cmd.bukkit.annotation.Permission;
import dev.triumphteam.cmd.core.annotations.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

@Command("discordchat")
@Permission(value = "discordchat.access")
public class BaseCommand {

    private final @NotNull DiscordChat plugin = JavaPlugin.getPlugin(DiscordChat.class);

    private final @NotNull SettingsManager config = this.plugin.getOptions();

    private final @NotNull SettingsManager locale = this.plugin.getLocale();

    @Command
    @Permission(value = "discordchat.help", def = PermissionDefault.TRUE)
    public void help(CommandSender sender) {
        this.locale.getProperty(Locale.help).forEach(sender::sendRichMessage);
    }

    @Command("reload")
    @Permission(value = "discordchat.reload", def = PermissionDefault.OP)
    public void reload(CommandSender sender) {
        // Reload the config files.
        this.config.reload();
        this.locale.reload();

        this.plugin.enableBot();

        // Send the reload message.
        sender.sendRichMessage(this.locale.getProperty(Locale.reload_plugin).replaceAll("\\{prefix}", this.config.getProperty(Config.command_prefix)));
    }
}