package com.ryderbelserion.discordchat.platform.discord;

import ch.jalu.configme.SettingsManager;
import com.ryderbelserion.discordchat.DiscordChat;
import com.ryderbelserion.discordchat.platform.impl.Config;
import com.ryderbelserion.discordchat.platform.impl.Locale;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class GuildListener extends ListenerAdapter {

    private final @NotNull DiscordChat plugin = JavaPlugin.getPlugin(DiscordChat.class);

    private final @NotNull SettingsManager config = this.plugin.getOptions();

    private final @NotNull SettingsManager locale = this.plugin.getLocale();

    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        Guild guild = event.getGuild();

        if (!this.config.getProperty(Config.guild_id).equalsIgnoreCase(guild.getId())) {
            this.config.setProperty(Config.guild_id, guild.getId());

            this.config.save();
            this.config.reload();
        }

        String message = this.locale.getProperty(Locale.server_started);

        if (message.isEmpty() || message.isBlank()) return;

        for (String id : this.config.getProperty(Config.channels)) {
            TextChannel channel = guild.getTextChannelById(id);

            if (channel == null) continue;

            channel.sendMessage(message).queue();
        }
    }
}