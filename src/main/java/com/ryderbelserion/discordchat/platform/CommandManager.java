package com.ryderbelserion.discordchat.platform;

import com.ryderbelserion.discordchat.DiscordChat;
import com.ryderbelserion.discordchat.platform.commands.types.ReloadCommand;
import dev.triumphteam.cmd.bukkit.BukkitCommandManager;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public class CommandManager {

    private final @NotNull DiscordChat plugin = JavaPlugin.getPlugin(DiscordChat.class);

    private final @NotNull BukkitCommandManager<CommandSender> commandManager = BukkitCommandManager.create(this.plugin);

    public void load() {
        List.of(
                new ReloadCommand()
        ).forEach(this.commandManager::registerCommand);
    }

    public @NotNull BukkitCommandManager<CommandSender> getCommandManager() {
        return this.commandManager;
    }
}