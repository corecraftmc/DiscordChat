package com.ryderbelserion.discordchat.platform.commands;

import ch.jalu.configme.SettingsManager;
import com.ryderbelserion.discordchat.DiscordChat;
import com.ryderbelserion.discordchat.platform.ConfigManager;
import dev.triumphteam.cmd.bukkit.annotation.Permission;
import dev.triumphteam.cmd.core.annotations.Command;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

@Command("discordchat")
@Permission(value = "discordchat.access")
public abstract class BaseCommand {

    protected final @NotNull DiscordChat plugin = JavaPlugin.getPlugin(DiscordChat.class);

    protected final @NotNull ConfigManager configManager = this.plugin.getConfigManager();

    protected final @NotNull SettingsManager config = this.configManager.getConfig();

}