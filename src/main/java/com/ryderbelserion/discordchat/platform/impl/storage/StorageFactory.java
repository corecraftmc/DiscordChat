package com.ryderbelserion.discordchat.platform.impl.storage;

import com.ryderbelserion.discordchat.DiscordChat;
import com.ryderbelserion.discordchat.platform.impl.storage.interfaces.StorageImplementation;
import com.ryderbelserion.discordchat.platform.impl.storage.sql.SqlStorage;
import com.ryderbelserion.discordchat.platform.impl.storage.sql.connection.file.SqliteType;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import java.io.File;

public class StorageFactory {

    private final @NotNull DiscordChat plugin = JavaPlugin.getPlugin(DiscordChat.class);

    private final Storage storage;

    public StorageFactory() {
        this.storage = new Storage(create(StorageType.sqlite));
    }

    public Storage getInstance() {
        return this.storage;
    }

    private StorageImplementation create(StorageType type) {
        switch (type) {
            case sqlite -> {
                return new SqlStorage(new SqliteType(new File(this.plugin.getDataFolder(), "users.db")));
            }

            case mariadb -> {

            }

            default -> throw new RuntimeException("Unknown storage type: " + type);
        }

        return null;
    }
}