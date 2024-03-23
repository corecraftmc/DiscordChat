package com.ryderbelserion.discordchat.platform.impl;

import ch.jalu.configme.Comment;
import ch.jalu.configme.SettingsHolder;
import ch.jalu.configme.properties.Property;
import java.util.Collections;
import java.util.List;
import static ch.jalu.configme.properties.PropertyInitializer.newListProperty;
import static ch.jalu.configme.properties.PropertyInitializer.newProperty;

public class Config implements SettingsHolder {

    @Comment("The prefix that appears in front of messages.")
    public static final Property<String> command_prefix = newProperty("root.prefix", "<gold>[<red>DiscordChat<gold>]: ");

    @Comment("The bot token from the discord developer dashboard.")
    public static final Property<String> bot_token = newProperty("guild.token", " ");

    @Comment("The guild id for the server.")
    public static final Property<String> guild_id = newProperty("guild.id", " ");

    @Comment("A list of channels where messages are sent.")
    public static final Property<List<String>> channels = newListProperty("guild.channels", Collections.emptyList());

}