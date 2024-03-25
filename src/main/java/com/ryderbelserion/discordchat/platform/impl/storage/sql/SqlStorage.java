package com.ryderbelserion.discordchat.platform.impl.storage.sql;

import com.ryderbelserion.discordchat.platform.impl.storage.sql.flatfile.ConnectionFactory;
import java.io.File;
import java.sql.SQLException;

public class SqlStorage implements SqlImpl {

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
    public void shutdown() throws SQLException {
        this.factory.shutdown();
    }

    @Override
    public File getFile() {
        return this.factory.getFile();
    }
}