package com.ryderbelserion.discordchat.platform.impl.storage.sql;

import com.ryderbelserion.discordchat.platform.impl.storage.interfaces.StorageImplementation;
import com.ryderbelserion.discordchat.platform.impl.storage.sql.connection.ConnectionFactory;

public class SqlStorage implements StorageImplementation {

    private final ConnectionFactory factory;

    public SqlStorage(ConnectionFactory factory) {
        this.factory = factory;
    }

    @Override
    public String getName() {
        return this.factory.getName();
    }

    @Override
    public void init() {
        this.factory.init();
    }

    @Override
    public void stop() throws Exception {
        this.factory.stop();
    }
}