package com.ryderbelserion.discordchat.platform.commands.types;

import com.ryderbelserion.discordchat.platform.commands.BaseCommand;
import com.ryderbelserion.discordchat.platform.impl.cache.CacheManager;
import com.ryderbelserion.discordchat.platform.utils.StringUtils;
import dev.triumphteam.cmd.bukkit.annotation.Permission;
import dev.triumphteam.cmd.core.annotations.Command;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionDefault;
import org.jetbrains.annotations.NotNull;
import java.util.UUID;

public class LinkCommand extends BaseCommand {

    private final @NotNull CacheManager cacheManager = this.plugin.getCacheManager();

    @Command("link")
    @Permission(value = "discordchat.link", def = PermissionDefault.OP)
    public void link(Player player) {
        UUID uuid = player.getUniqueId();

        if (this.cacheManager.hasUser(uuid)) {
            //todo() don't generate a code if user already exists.
            return;
        }

        String code = StringUtils.generate();

        if (this.cacheManager.getCode(code) != null) {
            //todo() send message if code already exists.
            return;
        }

        this.cacheManager.addUser(uuid, code);

        player.sendMessage("This is your code: " + code);
    }

    @Command("status")
    @Permission(value = "discordchat.status", def = PermissionDefault.OP)
    public void status(Player player) {
        UUID uuid = player.getUniqueId();
    }
}