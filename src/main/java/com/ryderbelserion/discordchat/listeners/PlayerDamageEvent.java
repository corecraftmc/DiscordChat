package com.ryderbelserion.discordchat.listeners;

import ch.jalu.configme.SettingsManager;
import com.ryderbelserion.discordchat.DiscordChat;
import com.ryderbelserion.discordchat.platform.ConfigManager;
import com.ryderbelserion.discordchat.platform.discord.DiscordBot;
import com.ryderbelserion.discordchat.platform.impl.Locale;
import com.ryderbelserion.discordchat.platform.impl.enums.Messages;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import java.util.HashMap;
import java.util.Map;

public class PlayerDamageEvent implements Listener {

    private final @NotNull DiscordChat plugin = JavaPlugin.getPlugin(DiscordChat.class);

    private final @NotNull SettingsManager locale = ConfigManager.getLocale();

    private final @NotNull DiscordBot bot = this.plugin.getBot();

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getPlayer();

        Component death = event.deathMessage();

        Map<String, String> placeholders = new HashMap<>() {{
            put("{username}", player.getName());

            if (death != null) {
                put("{cause}", PlainTextComponentSerializer.plainText().serialize(death));
            }
        }};

        this.bot.sendDiscordMessage(
                player,
                Messages.player_death_title.getMessage(player, placeholders),
                null,
                this.locale.getProperty(Locale.player_death_color)
        );
    }
}