package com.ryderbelserion.discordchat.listeners;

import ch.jalu.configme.SettingsManager;
import com.ryderbelserion.discordchat.DiscordChat;
import com.ryderbelserion.discordchat.platform.ConfigManager;
import com.ryderbelserion.discordchat.platform.discord.DiscordBot;
import com.ryderbelserion.discordchat.platform.impl.Locale;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class PlayerTrafficEvent implements Listener {

    private final @NotNull DiscordChat plugin = JavaPlugin.getPlugin(DiscordChat.class);

    private final @NotNull ConfigManager configManager = this.plugin.getConfigManager();

    private final @NotNull SettingsManager locale = this.configManager.getLocale();

    private final @NotNull DiscordBot bot = this.plugin.getDiscordBot();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        String title = this.locale.getProperty(Locale.player_join_title);
        String color = this.locale.getProperty(Locale.player_join_color);

        this.bot.sendDiscordMessage(player, title.replaceAll("\\{username}", player.getName()), color);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        String title = this.locale.getProperty(Locale.player_quit_title);
        String color = this.locale.getProperty(Locale.player_quit_color);

        this.bot.sendDiscordMessage(player, title.replaceAll("\\{username}", player.getName()), color);
    }
}
