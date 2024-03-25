package com.ryderbelserion.discordchat.platform.commands.types;

import com.ryderbelserion.discordchat.platform.commands.BaseCommand;
import dev.triumphteam.cmd.bukkit.annotation.Permission;
import dev.triumphteam.cmd.core.annotations.Command;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionDefault;

public class LinkCommand extends BaseCommand {

    @Command("link")
    @Permission(value = "discordchat.link", def = PermissionDefault.OP)
    public void link(Player player) {

    }
}