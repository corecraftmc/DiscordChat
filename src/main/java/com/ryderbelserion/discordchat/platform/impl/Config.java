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

    @Comment("Send the startup message.")
    public static final Property<Boolean> send_startup = newProperty("root.startup", false);

    @Comment("Send the shutdown message.")
    public static final Property<Boolean> send_shutdown = newProperty("root.shutdown", false);

    @Comment({
            "The timezone the discord bot should use by default.",
            "All timezones are identified by the TZ identifier.",
            "",
            "https://en.wikipedia.org/wiki/List_of_tz_database_time_zones"
    })
    public static final Property<String> timezone = newProperty("root.timezone", "America/New_York");

    @Comment("The bot token from the discord developer dashboard.")
    public static final Property<String> bot_token = newProperty("guild.token", " ");

    @Comment("The guild id for the server.")
    public static final Property<String> guild_id = newProperty("guild.id", " ");

    @Comment("A list of channels where messages are sent.")
    public static final Property<List<String>> channels = newListProperty("guild.channels", Collections.emptyList());

}