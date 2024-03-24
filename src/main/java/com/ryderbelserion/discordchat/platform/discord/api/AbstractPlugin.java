package com.ryderbelserion.discordchat.platform.discord.api;

import ch.jalu.configme.SettingsManager;
import com.ryderbelserion.discordchat.DiscordChat;
import com.ryderbelserion.discordchat.platform.ConfigManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.List;

public abstract class AbstractPlugin {

    protected final DiscordChat plugin = JavaPlugin.getPlugin(DiscordChat.class);

    protected final ConfigManager configManager = this.plugin.getConfigManager();

    protected final SettingsManager config = this.configManager.getConfig();

    protected final SettingsManager locale = this.configManager.getLocale();

    public abstract void start();

    public abstract void stop();

    public abstract void guild(Guild guild);

    public abstract JDA jda();

    public abstract List<String> channels();

    public abstract Guild guild();

    public abstract void register(ListenerAdapter listenerAdapter);

    public abstract void register(Listener listener);

}