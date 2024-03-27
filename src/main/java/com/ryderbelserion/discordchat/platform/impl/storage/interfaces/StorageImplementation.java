package com.ryderbelserion.discordchat.platform.impl.storage.interfaces;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

public interface StorageImplementation {

    String getName();

    void init() throws SQLException, IOException;

    void stop() throws Exception;

    void createUser(UUID uuid, String id) throws SQLException;

    void removeUser(UUID uuid) throws SQLException;

    void removeUser(String id) throws SQLException;

    String getIdentifier(String id) throws SQLException;

    String getPlayer(UUID uuid) throws SQLException;
}