package com.ryderbelserion.discordchat.platform.discord.api.commands;

import com.ryderbelserion.discordchat.DiscordChat;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public abstract class CommandEngine extends ListenerAdapter {

    protected final @NotNull DiscordChat plugin = JavaPlugin.getPlugin(DiscordChat.class);

    private final String name;
    private final String description;
    private final Permission permission;

    public CommandEngine(String name, String description, Permission permission) {
        this.name = name;
        this.description = description;
        this.permission = permission;
    }

    protected abstract void perform(CommandContext context);

    public void execute(CommandContext context) {
        perform(context);
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        CommandContext context = new CommandContext(event);

        if (!event.getName().equalsIgnoreCase(getName())) return;

        execute(context);
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public Permission getPermission() {
        return this.permission;
    }
}