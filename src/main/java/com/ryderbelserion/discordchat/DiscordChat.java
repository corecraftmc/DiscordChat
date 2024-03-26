package com.ryderbelserion.discordchat;

import com.ryderbelserion.discordchat.platform.CommandManager;
import com.ryderbelserion.discordchat.platform.ConfigManager;
import com.ryderbelserion.discordchat.platform.discord.DiscordBot;
import com.ryderbelserion.discordchat.platform.impl.storage.Storage;
import com.ryderbelserion.discordchat.platform.impl.storage.StorageFactory;
import org.bukkit.plugin.java.JavaPlugin;

public class DiscordChat extends JavaPlugin {

    private Storage storage;
    private DiscordBot bot;

    @Override
    public void onEnable() {
        ConfigManager.load();

        this.storage = new StorageFactory().getInstance();

        CommandManager.load();

        this.bot = new DiscordBot();
        this.bot.start();
    }

    @Override
    public void onDisable() {
        this.storage.stop();

        this.bot.stop();
    }

    public Storage getStorage() {
        return this.storage;
    }

    public DiscordBot getBot() {
        return this.bot;
    }
}