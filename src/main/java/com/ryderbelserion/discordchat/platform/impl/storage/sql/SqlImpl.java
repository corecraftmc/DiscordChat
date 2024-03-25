package com.ryderbelserion.discordchat.platform.impl.storage.sql;

import java.io.File;
import java.sql.SQLException;

public interface SqlImpl {

    String getName();

    void init();

    void shutdown() throws SQLException;

    File getFile();

}