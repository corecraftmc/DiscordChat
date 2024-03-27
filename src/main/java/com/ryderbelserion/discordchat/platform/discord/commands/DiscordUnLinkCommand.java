package com.ryderbelserion.discordchat.platform.discord.commands;

import com.ryderbelserion.discordchat.platform.discord.api.commands.CommandContext;
import com.ryderbelserion.discordchat.platform.discord.api.commands.CommandEngine;
import com.ryderbelserion.discordchat.platform.impl.storage.Storage;
import net.dv8tion.jda.api.Permission;

public class DiscordUnLinkCommand extends CommandEngine {

    private final Storage storage = this.plugin.getStorage();

    public DiscordUnLinkCommand() {
        super("unlink", "Allows you to unlink your discord and minecraft account.", Permission.MESSAGE_SEND);
    }

    @Override
    protected void perform(CommandContext context) {
        if (this.storage.getId(context.author().getId()) == null) {
            context.reply("You are not linked to anyone.", false);

            return;
        }

        this.storage.removeUser(context.author().getId());

        context.reply("You have successfully unlinked your discord account.", false);
    }
}