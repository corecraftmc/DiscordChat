package com.ryderbelserion.discordchat;

import com.ryderbelserion.discordchat.platform.CommandManager;
import com.ryderbelserion.discordchat.platform.ConfigManager;
import com.ryderbelserion.discordchat.platform.discord.DiscordBot;
import org.bukkit.plugin.java.JavaPlugin;

public class DiscordChat extends JavaPlugin {

    private ConfigManager configManager;

    private CommandManager commandManager;

    private DiscordBot discordBot;

    @Override
    public void onEnable() {
        this.configManager = new ConfigManager();
        this.configManager.load();

        this.commandManager = new CommandManager();
        this.commandManager.load();

        this.discordBot = new DiscordBot();
        this.discordBot.start();
    }

    @Override
    public void onDisable() {
        this.discordBot.stop();
    }

    public CommandManager getCommandManager() {
        return this.commandManager;
    }

    public ConfigManager getConfigManager() {
        return this.configManager;
    }

    public DiscordBot getDiscordBot() {
        return this.discordBot;
    }
}