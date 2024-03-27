package com.ryderbelserion.discordchat.platform.discord.api.commands;

import com.ryderbelserion.discordchat.platform.discord.api.commands.interfaces.CommandActor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.SelfUser;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

public class CommandContext implements CommandActor {

    private final SlashCommandInteractionEvent event;

    public CommandContext(SlashCommandInteractionEvent event) {
        this.event = event;
    }

    @Override
    public void reply(String message, boolean ephemeral) {
        this.event.reply(message).setEphemeral(ephemeral).queue();
    }

    @Override
    public void reply(MessageEmbed message, boolean ephemeral) {
        this.event.replyEmbeds(message).setEphemeral(ephemeral).queue();;
    }

    @Override
    public OptionMapping getOption(String option) {
        return this.event.getOption(option);
    }

    @Override
    public User author() {
        return this.event.getUser();
    }

    @Override
    public User creator() {
        return jda().getUserById("209853986646261762");
    }

    @Override
    public SelfUser bot() {
        return jda().getSelfUser();
    }

    @Override
    public Guild guild() {
        return this.event.getGuild();
    }

    @Override
    public JDA jda() {
        return this.event.getJDA();
    }
}