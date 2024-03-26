package com.ryderbelserion.discordchat.listeners;

import ch.jalu.configme.SettingsManager;
import com.ryderbelserion.discordchat.DiscordChat;
import com.ryderbelserion.discordchat.platform.ConfigManager;
import com.ryderbelserion.discordchat.platform.discord.DiscordBot;
import com.ryderbelserion.discordchat.platform.impl.Locale;
import com.ryderbelserion.discordchat.platform.impl.enums.Messages;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class PlayerTrafficEvent implements Listener {

    private final @NotNull DiscordChat plugin = JavaPlugin.getPlugin(DiscordChat.class);

    private final @NotNull SettingsManager locale = ConfigManager.getLocale();

    private final @NotNull DiscordBot bot = this.plugin.getBot();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        this.bot.sendDiscordMessage(
                player,
                Messages.player_join_title.getMessage(player, "{username}", player.getName()),
                null,
                this.locale.getProperty(Locale.player_join_color)
        );
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        this.bot.sendDiscordMessage(
                player,
                Messages.player_quit_title.getMessage(player, "{username}", player.getName()),
                null,
                this.locale.getProperty(Locale.player_quit_color)
        );
    }
}