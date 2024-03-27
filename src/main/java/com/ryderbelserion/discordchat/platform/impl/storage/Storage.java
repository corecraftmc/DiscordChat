package com.ryderbelserion.discordchat.platform.impl.storage;

import com.ryderbelserion.discordchat.DiscordChat;
import com.ryderbelserion.discordchat.platform.impl.storage.interfaces.StorageImplementation;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Level;

public class Storage {

    private final @NotNull DiscordChat plugin = JavaPlugin.getPlugin(DiscordChat.class);

    private final StorageImplementation implementation;

    public Storage(StorageImplementation implementation) {
        this.implementation = implementation;
    }

    public void init() {
        try {
            this.implementation.init();
        } catch (Exception exception) {
            this.plugin.getLogger().log(Level.SEVERE, "Failed to start storage implementation", exception);
        }
    }

    public void stop() {
        try {
            this.implementation.stop();
        } catch (Exception exception) {
            this.plugin.getLogger().log(Level.SEVERE, "Failed to stop storage implementation", exception);
        }
    }

    public StorageImplementation getImplementation() {
        return this.implementation;
    }

    public String getName() {
        return getImplementation().getName();
    }

    public String getUser(UUID uuid) {
        try {
            return this.implementation.getPlayer(uuid);
        } catch (SQLException exception) {
            exception.printStackTrace();

            return null;
        }
    }

    public void createUser(UUID uuid, String id) {
        try {
            this.implementation.createUser(uuid, id);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}