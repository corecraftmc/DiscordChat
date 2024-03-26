package com.ryderbelserion.discordchat;

import com.ryderbelserion.discordchat.platform.CommandManager;
import com.ryderbelserion.discordchat.platform.ConfigManager;
import com.ryderbelserion.discordchat.platform.discord.DiscordBot;
import org.bukkit.plugin.java.JavaPlugin;

public class DiscordChat extends JavaPlugin {

    private DiscordBot bot;

    @Override
    public void onEnable() {
        ConfigManager.load();

        CommandManager.load();

        this.bot = new DiscordBot();
        this.bot.start();
    }

    @Override
    public void onDisable() {
        this.bot.stop();
    }

    public DiscordBot getBot() {
        return this.bot;
    }
}