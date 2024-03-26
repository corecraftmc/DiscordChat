package com.ryderbelserion.discordchat.platform;

import ch.jalu.configme.SettingsManager;
import ch.jalu.configme.SettingsManagerBuilder;
import ch.jalu.configme.resource.YamlFileResourceOptions;
import com.ryderbelserion.discordchat.DiscordChat;
import com.ryderbelserion.discordchat.platform.impl.Config;
import com.ryderbelserion.discordchat.platform.impl.Locale;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class ConfigManager {

    private static final @NotNull DiscordChat plugin = JavaPlugin.getPlugin(DiscordChat.class);

    private static SettingsManager config;
    private static SettingsManager locale;

    public static void load() {
        YamlFileResourceOptions builder = YamlFileResourceOptions.builder().indentationSize(2).build();

        config = SettingsManagerBuilder
                .withYamlFile(new File(plugin.getDataFolder(), "config.yml"), builder)
                .useDefaultMigrationService()
                .configurationData(Config.class)
                .create();

        locale = SettingsManagerBuilder
                .withYamlFile(new File(plugin.getDataFolder(), "messages.yml"), builder)
                .useDefaultMigrationService()
                .configurationData(Locale.class)
                .create();
    }

    public static void reload() {
        config.reload();

        locale.reload();
    }

    public static SettingsManager getConfig() {
        return config;
    }

    public static SettingsManager getLocale() {
        return locale;
    }
}