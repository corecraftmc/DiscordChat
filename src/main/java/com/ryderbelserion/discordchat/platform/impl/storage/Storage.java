package com.ryderbelserion.discordchat.platform.impl.storage;

import com.ryderbelserion.discordchat.platform.impl.storage.sql.SqlImpl;

import java.sql.SQLException;

public class Storage {

    private final SqlImpl storage;

    public Storage(SqlImpl storage) {
        this.storage = storage;
    }

    public SqlImpl getStorage() {
        return this.storage;
    }

    public void init() {
        this.storage.init();
    }

    public void shutdown() {
        try {
            this.storage.shutdown();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}