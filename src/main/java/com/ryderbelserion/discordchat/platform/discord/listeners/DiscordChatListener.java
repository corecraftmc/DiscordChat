package com.ryderbelserion.discordchat.platform.discord.listeners;

import com.ryderbelserion.discordchat.DiscordChat;
import com.ryderbelserion.discordchat.platform.discord.DiscordBot;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class DiscordChatListener extends ListenerAdapter {

    private final @NotNull DiscordChat plugin = JavaPlugin.getPlugin(DiscordChat.class);

    private final @NotNull DiscordBot bot = this.plugin.getDiscordBot();

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        Message message = event.getMessage();
        User user = event.getAuthor();

        if (event.isWebhookMessage()) return;

        if (user.isBot() || user.isSystem()) return;

        String userName = user.getEffectiveName();
        String rawMessage = message.getContentRaw();

        this.bot.sendMinecraftMessage("{user} >> {message}".replaceAll("\\{user}", userName).replaceAll("\\{message}", rawMessage));
    }
}