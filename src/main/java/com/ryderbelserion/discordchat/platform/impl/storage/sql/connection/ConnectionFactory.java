package com.ryderbelserion.discordchat.platform.impl.storage.sql.connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.function.Function;

public interface ConnectionFactory {

    String getName();

    void init();

    void stop() throws Exception;

    Connection getConnection() throws SQLException;

    Function<String, String> getProcessor();

}