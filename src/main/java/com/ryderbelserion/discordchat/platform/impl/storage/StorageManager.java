package com.ryderbelserion.discordchat.platform.impl.storage;

import com.ryderbelserion.discordchat.DiscordChat;
import com.ryderbelserion.discordchat.platform.impl.storage.sql.SqlStorage;
import com.ryderbelserion.discordchat.platform.impl.storage.sql.flatfile.SqliteConnection;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import java.io.File;

public class StorageManager {

    private final @NotNull DiscordChat plugin = JavaPlugin.getPlugin(DiscordChat.class);

    private final Storage storage;

    public StorageManager() {
        this.storage = new Storage(create(StorageType.sqlite));
        this.storage.init();
    }

    public Storage get() {
        return this.storage;
    }

    public SqlStorage create(StorageType type) {
        switch (type) {
            case sqlite -> {
                return new SqlStorage(new SqliteConnection(new File(this.plugin.getDataFolder(), "users.db")));
            }

            case mysql -> {

            }

            default -> throw new RuntimeException("This method is not known: " + type);
        }

        return null;
    }
}