package com.ryderbelserion.discordchat.platform.commands.types;

import com.ryderbelserion.discordchat.platform.commands.BaseCommand;
import com.ryderbelserion.discordchat.platform.impl.cache.CacheManager;
import com.ryderbelserion.discordchat.platform.impl.storage.Storage;
import com.ryderbelserion.discordchat.platform.utils.StringUtils;
import dev.triumphteam.cmd.bukkit.annotation.Permission;
import dev.triumphteam.cmd.core.annotations.Command;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionDefault;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class UnlinkCommand extends BaseCommand {

    private final @NotNull Storage storage = this.plugin.getStorage();

    @Command("unlink")
    @Permission(value = "discordchat.unlink", def = PermissionDefault.OP)
    public void unlink(Player player) {
        //UUID uuid = player.getUniqueId();

        // Check if user is null.
        /*if (this.storage.getUser(uuid) == null) {
            // Send message.
            player.sendMessage("Cannot unlink since you haven't linked.");

            // Return because done.
            return;
        }*/

        // Send message.
        //player.sendMessage("Unlinked your minecraft account from your discord account.");

        // Remove the user.
        //this.storage.removeUser(uuid);
    }
}