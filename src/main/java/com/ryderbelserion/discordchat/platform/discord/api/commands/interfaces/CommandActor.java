package com.ryderbelserion.discordchat.platform.discord.api.commands.interfaces;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.SelfUser;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

public interface CommandActor {

    void reply(String message, boolean ephemeral);

    void reply(MessageEmbed message, boolean ephemeral);

    OptionMapping getOption(String option);

    User author();

    User creator();

    SelfUser bot();

    Guild guild();

    JDA jda();

}