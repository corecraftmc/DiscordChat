package com.ryderbelserion.discordchat.platform.impl;

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

    public static final Property<String> server_started = newProperty("server.started", "The server has started.");

    public static final Property<String> server_shutdown = newProperty("server.shutdown", "The server has shutdown.");
}