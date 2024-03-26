package com.ryderbelserion.discordchat.platform.impl.enums;

import ch.jalu.configme.SettingsManager;
import ch.jalu.configme.properties.Property;
import com.ryderbelserion.discordchat.platform.ConfigManager;
import com.ryderbelserion.discordchat.platform.impl.Config;
import com.ryderbelserion.discordchat.platform.impl.Locale;
import com.ryderbelserion.discordchat.platform.utils.StringUtils;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Messages {

    reloaded_plugin(Locale.reloaded_plugin),
    server_started(Locale.server_started),
    server_shutdown(Locale.server_shutdown),
    discord_chat_format(Locale.discord_chat_format),
    player_death_title(Locale.player_death_title),
    player_join_title(Locale.player_join_title),
    player_quit_title(Locale.player_quit_title),
    player_chat_format(Locale.player_chat_format),
    player_help(Locale.player_help, true);

    private Property<String> property;

    private Property<List<String>> properties;
    private boolean isList = false;

    Messages(Property<String> property) {
        this.property = property;
    }

    Messages(Property<List<String>> properties, boolean isList) {
        this.properties = properties;
        this.isList = isList;
    }

    private final @NotNull SettingsManager config = ConfigManager.getConfig();

    private final @NotNull SettingsManager messages = ConfigManager.getLocale();

    public @NotNull String getString() {
        return this.messages.getProperty(this.property);
    }

    public @NotNull List<String> getList() {
        return this.messages.getProperty(this.properties);
    }

    public boolean isList() {
        return this.isList;
    }

    public String getDiscordMessage() {
        return getDiscordMessage(new HashMap<>());
    }

    public String getDiscordMessage(String placeholder, String replacement) {
        Map<String, String> placeholders = new HashMap<>() {{
            put(placeholder, replacement);
        }};

        return getDiscordMessage(placeholders);
    }

    public String getDiscordMessage(Map<String, String> placeholders) {
        return parse(placeholders);
    }

    public String getMessage() {
        return getMessage(null, new HashMap<>());
    }

    public String getMessage(CommandSender sender) {
        if (sender instanceof Player player) {
            return getMessage(player, new HashMap<>());
        }

        return getMessage(null, new HashMap<>());
    }

    public String getMessage(Map<String, String> placeholders) {
        return getMessage(null, placeholders);
    }

    public String getMessage(String placeholder, String replacement) {
        return getMessage(null, placeholder, replacement);
    }

    public String getMessage(CommandSender sender, String placeholder, String replacement) {
        Map<String, String> placeholders = new HashMap<>() {{
            put(placeholder, replacement);
        }};

        if (sender instanceof Player player) {
            return getMessage(player, placeholders);
        }

        return getMessage(null, placeholders);
    }

    public String getMessage(Player player, Map<String, String> placeholders) {
        String prefix = this.config.getProperty(Config.command_prefix);

        String message = parse(placeholders);

        if (Support.placeholderapi.isPluginEnabled() && player != null) {
            return PlaceholderAPI.setPlaceholders(player, message.replaceAll("\\{prefix}", prefix));
        }

        return message.replaceAll("\\{prefix}", prefix);
    }

    private String parse(Map<String, String> placeholders) {
        String message;

        if (isList()) {
            message = StringUtils.convertList(getList());
        } else {
            message = getString();
        }

        if (!placeholders.isEmpty()) {
            for (Map.Entry<String, String> placeholder : placeholders.entrySet()) {
                message = message.replace(placeholder.getKey(), placeholder.getValue()).replace(placeholder.getKey().toLowerCase(), placeholder.getValue());
            }
        }

        return message;
    }
}