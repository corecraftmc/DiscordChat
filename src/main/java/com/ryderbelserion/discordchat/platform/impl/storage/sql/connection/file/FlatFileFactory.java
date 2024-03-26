package com.ryderbelserion.discordchat.platform.impl.storage.sql.connection.file;

import com.ryderbelserion.discordchat.platform.impl.storage.sql.connection.ConnectionFactory;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class FlatFileFactory implements ConnectionFactory {

    private Connection connection;
    private final File file;

    FlatFileFactory(File file) {
        this.file = file;
    }

    protected abstract Connection createConnection(File file) throws SQLException;

    protected File getFile() {
        return this.file;
    }

    @Override
    public void stop() throws Exception {
        if (this.connection != null) {
            this.connection.close();
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        Connection connection = this.connection;

        if (connection == null || connection.isClosed()) {
            connection = createConnection(this.file);
            this.connection = connection;
        }

        return this.connection;
    }
}