package com.ryderbelserion.discordchat.platform.impl.enums;

import com.ryderbelserion.discordchat.DiscordChat;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public enum Support {

    placeholderapi("PlaceholderAPI");

    private final String name;

    private final @NotNull DiscordChat plugin = JavaPlugin.getPlugin(DiscordChat.class);

    /**
     * @param name the name of the plugin.
     */
    Support(String name) {
        this.name = name;
    }

    /**
     * @return name of the plugin.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Checks if plugin is enabled.
     *
     * @return true or false.
     */
    public boolean isPluginEnabled() {
        return this.plugin.getServer().getPluginManager().isPluginEnabled(this.name);
    }
}