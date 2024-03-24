package com.ryderbelserion.discordchat.listeners;

import com.ryderbelserion.discordchat.DiscordChat;
import com.ryderbelserion.discordchat.platform.discord.DiscordBot;
import com.ryderbelserion.discordchat.platform.impl.enums.Messages;
import io.papermc.paper.event.player.AsyncChatEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import java.util.HashMap;
import java.util.Map;

public class PlayerChatEvent implements Listener {

    private final @NotNull DiscordChat plugin = JavaPlugin.getPlugin(DiscordChat.class);

    private final @NotNull DiscordBot bot = this.plugin.getDiscordBot();

    @EventHandler
    public void onPlayerChat(AsyncChatEvent event) {
        Player player = event.getPlayer();

        Map<String, String> placeholders = new HashMap<>() {{
            put("{username}", player.getName());
            put("{message}", event.signedMessage().message());
        }};

        this.bot.sendDiscordMessage(Messages.player_chat_format.getMessage(player, placeholders));
    }
}