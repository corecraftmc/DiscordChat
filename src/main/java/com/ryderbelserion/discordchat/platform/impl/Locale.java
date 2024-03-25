package com.ryderbelserion.discordchat.platform.impl;

import ch.jalu.configme.Comment;
import ch.jalu.configme.SettingsHolder;
import ch.jalu.configme.configurationdata.CommentsConfiguration;
import ch.jalu.configme.properties.Property;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import static ch.jalu.configme.properties.PropertyInitializer.newListProperty;
import static ch.jalu.configme.properties.PropertyInitializer.newProperty;

public class Locale implements SettingsHolder {

    @Override
    public void registerComments(@NotNull CommentsConfiguration conf) {
        String[] header = {
                "",
                "List of all sounds: https://jd.papermc.io/paper/1.20/org/bukkit/Sound.html",
                "List of all enchantments: https://jd.papermc.io/paper/1.20/org/bukkit/enchantments/Enchantment.html",
                "",
                "All the messages have PlaceholderAPI Support!."
        };

        conf.setComment("root", header);
    }

    public static final Property<String> reloaded_plugin = newProperty("root.reload-plugin", "{prefix}<red>You have reloaded the plugin.");

    public static final Property<List<String>> player_help = newListProperty("root.player-help", List.of(
            "<red>DiscordChat Player Help",
            "",
            " <red>/discordchat <gray>- <gold>Shows this menu.",
            " <red>/discordchat reload <gray>- <gold>Reloads the plugin."
    ));

    @Comment("The message sent when the server starts up.")
    public static final Property<String> server_started = newProperty("server.started.title", "The server has started.");

    @Comment("The color of start message.")
    public static final Property<String> server_started_color = newProperty("server.started.color", "#5e68ff");

    @Comment("The message sent when the server shuts down.")
    public static final Property<String> server_shutdown = newProperty("server.shutdown.title", "The server has shutdown.");

    @Comment("The color of stop message.")
    public static final Property<String> server_shutdown_color = newProperty("server.shutdown.color", "#5e68ff");

    @Comment({
            "The format of the message sent to minecraft.",
            "",
            "A list of placeholders supported: {color}, {role}, {username}, {message}"
    })
    public static final Property<String> discord_chat_format = newProperty("discord.chat.format", "<dark_gray>[<red>Discord</red>] {color}{role} {username} » {message}");

    public static final Property<String> player_death_color = newProperty("player.embeds.death-format.color", "#eb6123");

    @Comment("A list of placeholders supported: {username}, {killer}, {weapon}, {cause}")
    public static final Property<String> player_death_title = newProperty("player.embeds.death-format.title", "{cause}");

    @Comment("The color of the player join embed.")
    public static final Property<String> player_join_color = newProperty("player.embeds.join-format.color", "#0eeb6a");

    @Comment("A list of placeholders supported: {username}")
    public static final Property<String> player_join_title = newProperty("player.embeds.join-format.title", "{username} has joined the server.");

    @Comment("The color of the player quit embed.")
    public static final Property<String> player_quit_color = newProperty("player.embeds.quit-format.color", "#e0240b");

    @Comment("A list of placeholders supported: {username}")
    public static final Property<String> player_quit_title = newProperty("player.embeds.quit-format.title", "{username} has left the server.");

    @Comment({
            "The format of the message sent to discord.",
            "",
            "A list of placeholders supported: {username}, {message}"
    })
    public static final Property<String> player_chat_format = newProperty("player.chat.format", "{username} » {message}");

}