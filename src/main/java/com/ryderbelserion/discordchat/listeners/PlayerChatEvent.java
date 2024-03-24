package com.ryderbelserion.discordchat.listeners;

import ch.jalu.configme.SettingsManager;
import com.ryderbelserion.discordchat.DiscordChat;
import com.ryderbelserion.discordchat.platform.ConfigManager;
import com.ryderbelserion.discordchat.platform.discord.DiscordBot;
import com.ryderbelserion.discordchat.platform.impl.Locale;
import io.papermc.paper.event.player.AsyncChatEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class PlayerChatEvent implements Listener {

    private final @NotNull DiscordChat plugin = JavaPlugin.getPlugin(DiscordChat.class);

    private final @NotNull ConfigManager configManager = this.plugin.getConfigManager();

    private final @NotNull SettingsManager locale = this.configManager.getLocale();

    private final @NotNull DiscordBot bot = this.plugin.getDiscordBot();

    @EventHandler
    public void onPlayerChat(AsyncChatEvent event) {
        Player player = event.getPlayer();

        String message = event.signedMessage().message();

        String format = this.locale.getProperty(Locale.player_msg_format);
        String title = this.locale.getProperty(Locale.player_msg_title);
        String color = this.locale.getProperty(Locale.player_msg_color);

        this.bot.sendDiscordMessage(player, title.replaceAll("\\{username}", player.getName()), format.replaceAll("\\{message}", message), color);
    }
}