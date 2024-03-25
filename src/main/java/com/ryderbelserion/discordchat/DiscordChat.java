package com.ryderbelserion.discordchat;

import com.ryderbelserion.discordchat.platform.CommandManager;
import com.ryderbelserion.discordchat.platform.ConfigManager;
import com.ryderbelserion.discordchat.platform.discord.DiscordBot;
import com.ryderbelserion.discordchat.platform.impl.storage.Storage;
import com.ryderbelserion.discordchat.platform.impl.storage.StorageManager;
import org.bukkit.plugin.java.JavaPlugin;

public class DiscordChat extends JavaPlugin {

    private CommandManager commandManager;
    private ConfigManager configManager;
    private DiscordBot discordBot;

    private Storage storage;

    @Override
    public void onEnable() {
        this.configManager = new ConfigManager();
        this.configManager.load();

        StorageManager storageManager = new StorageManager();
        this.storage = storageManager.get();

        this.commandManager = new CommandManager();
        this.commandManager.load();

        this.discordBot = new DiscordBot();
        this.discordBot.start();
    }

    @Override
    public void onDisable() {
        this.storage.shutdown();

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

    public Storage getStorage() {
        return this.storage;
    }
}