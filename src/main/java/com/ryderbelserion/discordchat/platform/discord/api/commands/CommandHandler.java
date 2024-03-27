package com.ryderbelserion.discordchat.platform.discord.api.commands;

import com.ryderbelserion.discordchat.platform.discord.api.commands.interfaces.CommandFlow;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import java.util.List;

public class CommandHandler implements CommandFlow {

    private JDA jda;

    private Guild guild;

    public void setGuild(Guild guild) {
        this.guild = guild;
    }

    public void setJDA(JDA jda) {
        this.jda = jda;
    }

    @Override
    public void addCommand(CommandEngine engine) {
        this.jda.upsertCommand(engine.getName(), engine.getDescription()).queue();
    }

    @Override
    public void addCommand(CommandEngine engine, OptionType type, String name, String description) {
        this.jda.upsertCommand(engine.getName(), engine.getDescription()).addOption(type, name, description).queue();
    }

    @Override
    public void addCommand(CommandEngine engine, OptionData optionData) {
        this.jda.upsertCommand(engine.getName(), engine.getDescription()).addOptions(optionData).queue();
    }

    @Override
    public void addGuildCommand(CommandEngine engine) {
        this.guild.upsertCommand(engine.getName(), engine.getDescription()).queue();
    }

    @Override
    public void addGuildCommand(CommandEngine engine, OptionType type, String name, String description) {
        this.guild.upsertCommand(engine.getName(), engine.getDescription()).addOption(type, name, description).queue();
    }

    @Override
    public void addGuildCommand(CommandEngine engine, OptionData optionData) {
        this.guild.upsertCommand(engine.getName(), engine.getDescription()).addOptions(optionData).queue();
    }

    @Override
    public void addGuildCommands(List<CommandEngine> commands) {
        commands.forEach(this::addGuildCommand);
    }

    @Override
    public void addCommands(List<CommandEngine> commands) {
        commands.forEach(this::addCommand);
    }

    @Override
    public void purgeGuildCommands() {
        this.guild.updateCommands().queue();
    }

    @Override
    public void purgeGlobalCommands() {
        this.jda.updateCommands().queue();
    }
}