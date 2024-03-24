package com.ryderbelserion.discordchat.platform.impl;

import ch.jalu.configme.Comment;
import ch.jalu.configme.SettingsHolder;
import ch.jalu.configme.properties.Property;
import java.util.List;
import static ch.jalu.configme.properties.PropertyInitializer.newListProperty;
import static ch.jalu.configme.properties.PropertyInitializer.newProperty;

public class Locale implements SettingsHolder {

    public static final Property<String> reload_plugin = newProperty("root.reload-plugin", "{prefix}<red>You have reloaded the plugin.");

    public static final Property<List<String>> help = newListProperty("root.help-menu", List.of(
            "<red>DiscordChat Help Menu",
            "",
            " <red>/discordchat <gray>- <gold>Shows this menu.",
            " <red>/discordchat reload <gray>- <gold>Reloads the plugin."
    ));

    @Comment("The message sent when the server starts up.")
    public static final Property<String> server_started = newProperty("server.started", "The server has started.");

    @Comment("The message sent when the server shuts down.")
    public static final Property<String> server_shutdown = newProperty("server.shutdown", "The server has shutdown.");

    @Comment("The color of the player join embed.")
    public static final Property<String> player_join_color = newProperty("player.embeds.join.color", "#0eeb6a");

    public static final Property<String> player_join_title = newProperty("player.embeds.join.format.title", "{username} has joined the server.");

    @Comment("The color of the player quit embed.")
    public static final Property<String> player_quit_color = newProperty("player.embeds.quit.color", "#e0240b");

    public static final Property<String> player_quit_title = newProperty("player.embeds.quit.format.title", "{username} has left the server.");

    @Comment("The title format of the embed.")
    public static final Property<String> player_msg_title = newProperty("player.embeds.msg.format.title", "{username}");

    @Comment("The format of messages sent to discord.")
    public static final Property<String> player_msg_format = newProperty("player.embeds.msg.format.description", "{message}");

    @Comment("The color of the message embed.")
    public static final Property<String> player_msg_color = newProperty("player.embeds.msg.color", "#bff7fd");
}