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

    private final @NotNull DiscordChat plugin = JavaPlugin.getPlugin(DiscordChat.class);

    private SettingsManager config;
    private SettingsManager locale;

    public void load() {
        // Create config files
        YamlFileResourceOptions builder = YamlFileResourceOptions.builder().indentationSize(2).build();

        this.config = SettingsManagerBuilder
                .withYamlFile(new File(this.plugin.getDataFolder(), "config.yml"), builder)
                .useDefaultMigrationService()
                .configurationData(Config.class)
                .create();

        this.locale = SettingsManagerBuilder
                .withYamlFile(new File(this.plugin.getDataFolder(), "messages.yml"), builder)
                .useDefaultMigrationService()
                .configurationData(Locale.class)
                .create();
    }

    public void reload() {
        this.config.reload();

        this.locale.reload();
    }

    public SettingsManager getConfig() {
        return this.config;
    }

    public SettingsManager getLocale() {
        return this.locale;
    }
}