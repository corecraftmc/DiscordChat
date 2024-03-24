package com.ryderbelserion.discordchat.platform.impl

import ch.jalu.configme.SettingsHolder
import ch.jalu.configme.properties.Property
import ch.jalu.configme.properties.PropertyInitializer

object Locale : SettingsHolder {

    val reload_plugin: Property<String> = PropertyInitializer.newProperty("root.reload-plugin", "{prefix}<red>You have reloaded the plugin.")

    val help: Property<List<String>> = PropertyInitializer.newListProperty(
        "root.help-menu", listOf(
            "<red>DiscordChat Help Menu",
            "",
            " <red>/discordchat <gray>- <gold>Shows this menu.",
            " <red>/discordchat reload <gray>- <gold>Reloads the plugin."
        )
    )

    val server_started: Property<String> = PropertyInitializer.newProperty("server.started", "The server has started.")

    val server_shutdown: Property<String> = PropertyInitializer.newProperty("server.shutdown", "The server has shutdown.")
}