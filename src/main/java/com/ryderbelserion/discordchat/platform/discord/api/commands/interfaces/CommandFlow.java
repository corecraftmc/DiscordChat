package com.ryderbelserion.discordchat.platform.discord.api.commands.interfaces;

import com.ryderbelserion.discordchat.platform.discord.api.commands.CommandEngine;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.List;

public interface CommandFlow {

    void addCommand(CommandEngine engine);

    void addCommand(CommandEngine engine, OptionData optionData);

    void addCommand(CommandEngine engine, OptionType type, String name, String description);

    void addGuildCommand(CommandEngine engine);

    void addGuildCommand(CommandEngine engine, OptionData optionData);

    void addGuildCommand(CommandEngine engine, OptionType type, String name, String description);

    void addGuildCommands(List<CommandEngine> commands);

    void addCommands(List<CommandEngine> commands);

    void purgeGuildCommands();

    void purgeGlobalCommands();

}