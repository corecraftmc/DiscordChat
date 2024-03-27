package com.ryderbelserion.discordchat.platform;

import com.ryderbelserion.discordchat.DiscordChat;
import com.ryderbelserion.discordchat.platform.commands.types.LinkCommand;
import com.ryderbelserion.discordchat.platform.commands.types.ReloadCommand;
import com.ryderbelserion.discordchat.platform.commands.types.UnlinkCommand;
import dev.triumphteam.cmd.bukkit.BukkitCommandManager;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public class CommandManager {

    private static final @NotNull DiscordChat plugin = JavaPlugin.getPlugin(DiscordChat.class);

    private static final @NotNull BukkitCommandManager<CommandSender> commandManager = BukkitCommandManager.create(plugin);

    public static void load() {
        List.of(
                new ReloadCommand(),
                new UnlinkCommand(),
                new LinkCommand()
        ).forEach(commandManager::registerCommand);
    }

    public static @NotNull BukkitCommandManager<CommandSender> getCommandManager() {
        return commandManager;
    }
}