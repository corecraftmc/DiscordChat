package com.ryderbelserion.discordchat.platform.discord.listeners;

import com.ryderbelserion.discordchat.DiscordChat;
import com.ryderbelserion.discordchat.platform.discord.DiscordBot;
import com.ryderbelserion.discordchat.platform.discord.api.utils.ColorUtils;
import com.ryderbelserion.discordchat.platform.discord.api.utils.RoleUtils;
import com.ryderbelserion.discordchat.platform.impl.enums.Messages;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class DiscordChatListener extends ListenerAdapter {

    private final @NotNull DiscordChat plugin = JavaPlugin.getPlugin(DiscordChat.class);

    private final @NotNull DiscordBot bot = this.plugin.getBot();

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        Message message = event.getMessage();
        User user = event.getAuthor();

        if (event.isWebhookMessage()) return;

        if (user.isBot() || user.isSystem()) return;

        Member member = event.getMember();

        if (member == null) return;

        String userName = member.getEffectiveName();
        String rawMessage = message.getContentRaw();

        Map<String, String> placeholders = new HashMap<>();
        placeholders.put("{username}", userName);
        placeholders.put("{message}", rawMessage);

        Role role = RoleUtils.getHighestRole(member);

        if (role != null) {
            placeholders.put("{role}", role.getName());

            Color color = role.getColor();

            if (color != null) {
                placeholders.put("{color}", "<#" + ColorUtils.toHex(color) + ">");
            } else {
                Role highestRole = RoleUtils.getHighestRoleWithColor(member);

                if (highestRole != null) {
                    Color highestColor = highestRole.getColor();

                    if (highestColor != null) {
                        placeholders.put("{color}", "<#" + ColorUtils.toHex(highestColor) + ">");
                    }
                }
            }
        }

        this.bot.sendMinecraftMessage(Messages.discord_chat_format.getDiscordMessage(placeholders));
    }
}