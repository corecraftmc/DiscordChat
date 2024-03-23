package com.ryderbelserion.discordchat.listeners;

import io.papermc.paper.event.player.AsyncChatEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class PlayerChatEvent implements Listener {

    public void onPlayerChat(AsyncChatEvent event) {
        Player player = event.getPlayer();

        String message = event.signedMessage().message();
    }
}