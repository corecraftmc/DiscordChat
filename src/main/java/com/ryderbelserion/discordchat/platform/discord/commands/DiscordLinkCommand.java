package com.ryderbelserion.discordchat.platform.discord.commands;

import com.ryderbelserion.discordchat.platform.discord.api.commands.CommandContext;
import com.ryderbelserion.discordchat.platform.discord.api.commands.CommandEngine;
import com.ryderbelserion.discordchat.platform.impl.cache.CacheManager;
import com.ryderbelserion.discordchat.platform.impl.storage.Storage;
import net.dv8tion.jda.api.Permission;
import java.util.UUID;

public class DiscordLinkCommand extends CommandEngine {

    private final Storage storage = this.plugin.getStorage();

    private final CacheManager cacheManager = this.plugin.getCacheManager();

    public DiscordLinkCommand() {
        super("link", "Allows you to link your discord and minecraft account.", Permission.MESSAGE_SEND);
    }

    @Override
    protected void perform(CommandContext context) {
        String option = context.getOption("code").getAsString();

        // Get the code.
        String code = this.cacheManager.getCode(option);

        // If code is null, say it's invalid then return.
        if (code == null) {
            context.reply("The code you entered is invalid!", false);

            return;
        }

        // Get uuid by code.
        UUID uuid = this.cacheManager.getIdentifier(code);

        if (this.storage.getUser(uuid) != null) {
            // Remove the cache.
            this.cacheManager.removeUser(uuid);

            // Send message.
            context.reply("The user already is linked, Please use /unlink to start over", false);

            return;
        }

        // Send successfully linked.
        context.reply("You have successfully linked your discord account to " + this.cacheManager.getIdentifier(code), false);

        // Store user.
        this.storage.createUser(uuid, context.author().getId());

        // Remove from cache.
        this.cacheManager.removeUser(uuid);
    }
}