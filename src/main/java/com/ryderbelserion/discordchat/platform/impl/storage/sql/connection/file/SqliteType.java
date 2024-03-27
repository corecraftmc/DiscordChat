package com.ryderbelserion.discordchat.platform.impl.storage.sql.connection.file;

import com.ryderbelserion.discordchat.platform.impl.storage.StorageType;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.function.Function;

public class SqliteType extends FlatFileFactory {

    public SqliteType(File file) {
        super(file);
    }

    @Override
    protected Connection createConnection(File file) throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC").getDeclaredConstructor().newInstance();

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
        return StorageType.sqlite.getImpl();
    }

    @Override
    public void init() {
        try {
            getFile().createNewFile();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public Function<String, String> getProcessor() {
        return s -> s.replace('\'', '`');
    }
}