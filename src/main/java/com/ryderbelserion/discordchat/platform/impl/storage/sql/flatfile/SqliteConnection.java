package com.ryderbelserion.discordchat.platform.impl.storage.sql.flatfile;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteConnection extends ConnectionFactory {

    public SqliteConnection(File file) {
        super(file);
    }

    @Override
    protected Connection create() throws SQLException {
        try {
            Class.forName("org.sqlite.jdbc4.JDBC4Connection").getDeclaredConstructor().newInstance();

            return DriverManager.getConnection("jdbc:sqlite:" + getFile());
        } catch (ReflectiveOperationException exception) {
            if (exception.getCause() instanceof SQLException) {
                throw (SQLException) exception.getCause();
            }

            throw new RuntimeException(exception);
        }
    }

    @Override
    public String getName() {
        return "SQLite";
    }

    @Override
    public void init() {
        try {
            getFile().createNewFile();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}