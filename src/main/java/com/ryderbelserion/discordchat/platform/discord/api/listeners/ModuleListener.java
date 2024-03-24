package com.ryderbelserion.discordchat.platform.discord.api.listeners;

import com.ryderbelserion.discordchat.platform.discord.api.AbstractPlugin;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class ModuleListener extends ListenerAdapter {

    private final AbstractPlugin plugin;

    public ModuleListener(AbstractPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        this.plugin.guild(event.getGuild());
    }
}