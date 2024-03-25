package com.ryderbelserion.discordchat.platform.impl.storage.sql.flatfile;

import com.ryderbelserion.discordchat.platform.impl.storage.sql.SqlImpl;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class ConnectionFactory implements SqlImpl {

    private final File file;

    public ConnectionFactory(File file) {
        this.file = file;
    }

    private Connection connection;

    protected abstract Connection create() throws SQLException;

    public Connection getConnection() throws SQLException {
        Connection connection = this.connection;

        if (connection == null || connection.isClosed()) {
            connection = create();
            this.connection = connection;
        }

        return connection;
    }

    @Override
    public void shutdown() throws SQLException {
        if (this.connection != null) {
            this.connection.close();
        }
    }

    @Override
    public File getFile() {
        return this.file;
    }
}