package com.ryderbelserion.discordchat.platform.commands.types;

import com.ryderbelserion.discordchat.platform.commands.BaseCommand;
import com.ryderbelserion.discordchat.platform.impl.storage.Storage;
import dev.triumphteam.cmd.bukkit.annotation.Permission;
import dev.triumphteam.cmd.core.annotations.Command;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionDefault;
import org.jetbrains.annotations.NotNull;
import java.util.UUID;

public class LinkCommand extends BaseCommand {

    private final @NotNull Storage storage = this.plugin.getStorage();

    @Command("link")
    @Permission(value = "discordchat.link", def = PermissionDefault.OP)
    public void link(Player player) {
        UUID uuid = player.getUniqueId();

        this.storage.createUser(uuid);
    }
}