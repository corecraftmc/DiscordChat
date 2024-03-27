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

        String code = this.cacheManager.getCode(option);

        if (code == null) {
            context.reply("The code you entered is invalid!", false);
            return;
        }

        UUID uuid = this.cacheManager.getIdentifier(code);

        context.reply("You have successfully linked your discord account to " + this.cacheManager.getIdentifier(code), false);

        this.storage.createUser(uuid, context.author().getId());

        this.cacheManager.removeUser(uuid);
    }
}