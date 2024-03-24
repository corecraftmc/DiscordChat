package com.ryderbelserion.discordchat.platform.impl

import ch.jalu.configme.Comment
import ch.jalu.configme.SettingsHolder
import ch.jalu.configme.properties.Property
import ch.jalu.configme.properties.PropertyInitializer

object Config : SettingsHolder {

    @Comment("The prefix that appears in front of messages.")
    val command_prefix: Property<String> = PropertyInitializer.newProperty("root.prefix", "<gold>[<red>DiscordChat<gold>]: ")

    @Comment("The bot token from the discord developer dashboard.")
    val bot_token: Property<String> = PropertyInitializer.newProperty("root.bot.token", " ")

    //@Comment("Enable sending messages through a discord webhook.")
    //public static final Property<Boolean> use_guild_webhook = newProperty("guild.webhook.toggle", false);
    //@Comment("The webhook to send messages to.")
    //public static final Property<String> guild_webhook = newProperty("guild.webhook.value", "");

    @Comment("The guild id for the server.")
    val guild_id: Property<String> = PropertyInitializer.newProperty("guild.id", " ")

    @Comment("A list of channels where messages are sent.")
    val channels: Property<List<String>> = PropertyInitializer.newListProperty("guild.channels", emptyList())
}