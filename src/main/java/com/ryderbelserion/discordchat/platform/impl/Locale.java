package com.ryderbelserion.discordchat.platform.impl;

import ch.jalu.configme.Comment;
import ch.jalu.configme.SettingsHolder;
import ch.jalu.configme.properties.Property;
import java.util.List;
import static ch.jalu.configme.properties.PropertyInitializer.newListProperty;
import static ch.jalu.configme.properties.PropertyInitializer.newProperty;

public class Locale implements SettingsHolder {

    public static final Property<String> reloaded_plugin = newProperty("root.reload-plugin", "{prefix}<red>You have reloaded the plugin.");

    public static final Property<List<String>> player_help = newListProperty("root.player-help", List.of(
            "<red>DiscordChat Player Help",
            "",
            " <red>/discordchat <gray>- <gold>Shows this menu.",
            " <red>/discordchat reload <gray>- <gold>Reloads the plugin."
    ));

    @Comment("The message sent when the server starts up.")
    public static final Property<String> server_started = newProperty("server.started", "The server has started.");

    @Comment("The message sent when the server shuts down.")
    public static final Property<String> server_shutdown = newProperty("server.shutdown", "The server has shutdown.");

    @Comment("The color of the player join embed.")
    public static final Property<String> player_join_color = newProperty("player.embeds.join-format.color", "#0eeb6a");

    public static final Property<String> player_join_title = newProperty("player.embeds.join-format.title", "{username} has joined the server.");

    @Comment("The color of the player quit embed.")
    public static final Property<String> player_quit_color = newProperty("player.embeds.quit-format.color", "#e0240b");

    public static final Property<String> player_quit_title = newProperty("player.embeds.quit-format.title", "{username} has left the server.");

    @Comment("The format of the message sent to discord.")
    public static final Property<String> player_chat_format = newProperty("player.chat.format", "{username} » {message}");
}