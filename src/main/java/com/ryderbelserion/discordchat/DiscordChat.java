package com.ryderbelserion.discordchat;

import ch.jalu.configme.SettingsManager;
import ch.jalu.configme.SettingsManagerBuilder;
import ch.jalu.configme.resource.YamlFileResourceOptions;
import com.ryderbelserion.discordchat.platform.BaseCommand;
import com.ryderbelserion.discordchat.platform.discord.GuildListener;
import com.ryderbelserion.discordchat.platform.impl.Config;
import com.ryderbelserion.discordchat.platform.impl.Locale;
import dev.triumphteam.cmd.bukkit.BukkitCommandManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import java.io.File;

public class DiscordChat extends JavaPlugin {

    private JDA jda;

    private SettingsManager config;
    private SettingsManager locale;

    @Override
    public void onEnable() {
        // Create config files
        YamlFileResourceOptions builder = YamlFileResourceOptions.builder().indentationSize(2).build();

        this.config = SettingsManagerBuilder
                .withYamlFile(new File(getDataFolder(), "config.yml"), builder)
                .useDefaultMigrationService()
                .configurationData(Config.class)
                .create();

        this.locale = SettingsManagerBuilder
                .withYamlFile(new File(getDataFolder(), "messages.yml"), builder)
                .useDefaultMigrationService()
                .configurationData(Locale.class)
                .create();

        BukkitCommandManager<CommandSender> command = BukkitCommandManager.create(this);

        command.registerCommand(new BaseCommand());

        enableBot();
    }

    @Override
    public void onDisable() {
        if (this.jda != null) {
            String guild_id = this.config.getProperty(Config.guild_id);

            Guild guild = this.jda.getGuildById(guild_id);

            String message = this.locale.getProperty(Locale.server_shutdown);

            if (guild != null) {
                for (String id : this.config.getProperty(Config.channels)) {
                    TextChannel channel = guild.getTextChannelById(id);

                    if (channel == null) continue;

                    channel.sendMessage(message).queue();
                }
            }

            this.jda.shutdown();
        }
    }

    private void sendMessage(String message) {
        if (!message.isEmpty() || !message.isBlank()) {
            Guild guild = this.jda.getGuildById("1142362341095251998");

            if (guild != null) {
                TextChannel channel = guild.getTextChannelById("1142362341703438359");

                if (channel != null) {
                    channel.sendMessage(message).queue();
                }
            }
        }
    }

    public @NotNull SettingsManager getOptions() {
        return this.config;
    }

    public @NotNull SettingsManager getLocale() {
        return this.locale;
    }

    public void enableBot() {
        if (this.jda != null) {
            getLogger().warning("The bot is already enabled!");

            return;
        }

        String token = this.config.getProperty(Config.bot_token);

        if (token.isBlank()) {
            getLogger().warning("Token cannot be blank or empty.");

            return;
        }

        this.jda = JDABuilder.createLight(this.config.getProperty(Config.bot_token))
                .addEventListeners(new GuildListener())
                .build();
    }

    public @NotNull JDA getJda() {
        return this.jda;
    }
}