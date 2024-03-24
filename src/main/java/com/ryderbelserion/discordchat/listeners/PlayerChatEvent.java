package com.ryderbelserion.discordchat.listeners;

import com.ryderbelserion.discordchat.DiscordChat;
import com.ryderbelserion.discordchat.platform.discord.DiscordBot;
import io.papermc.paper.event.player.AsyncChatEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class PlayerChatEvent implements Listener {

    private final @NotNull DiscordChat plugin = JavaPlugin.getPlugin(DiscordChat.class);

    private final @NotNull DiscordBot bot = this.plugin.getDiscordBot();

    @EventHandler
    public void onPlayerChat(AsyncChatEvent event) {
        Player player = event.getPlayer();

        String message = event.signedMessage().message();

        String format = "{name} > {message}";

        this.bot.sendDiscordMessage(format.replaceAll("\\{name}", player.getName()).replaceAll("\\{message}", message));
    }
}