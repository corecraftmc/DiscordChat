package com.ryderbelserion.discordchat.platform.impl.storage.sql;

import com.ryderbelserion.discordchat.DiscordChat;
import com.ryderbelserion.discordchat.platform.impl.storage.interfaces.StorageImplementation;
import com.ryderbelserion.discordchat.platform.impl.storage.sql.connection.ConnectionFactory;
import com.ryderbelserion.discordchat.platform.utils.StringUtils;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import java.io.IOException;
import java.io.InputStream;
import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;

public class SqlStorage implements StorageImplementation {

    private final @NotNull DiscordChat plugin = JavaPlugin.getPlugin(DiscordChat.class);

    private final ConnectionFactory factory;
    private final Function<String, String> processor;

    public SqlStorage(ConnectionFactory factory) {
        this.factory = factory;

        this.processor = this.factory.getProcessor().compose(value -> value.replace("{prefix}", "discord_"));
    }

    @Override
    public String getName() {
        return this.factory.getName();
    }

    @Override
    public void init() throws SQLException, IOException {
        this.factory.init();

        boolean tableExists;

        try (Connection connection = this.factory.getConnection()) {
            tableExists = tableExists(connection, this.processor.apply("{prefix}players"));
        }

        if (!tableExists) {
            schema();
        }
    }

    private void schema() throws IOException, SQLException {
        List<String> statements;

        String file = "schema/" + getName().toLowerCase(Locale.ROOT) + ".sql";

        try (InputStream inputStream = this.plugin.getResource(file)) {
            if (inputStream == null) {
                return;
            }

            statements = StringUtils.getStatements(inputStream).stream().map(this.processor).toList();
        }

        try (Connection connection = this.factory.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                for (String query : statements) {
                    statement.addBatch(query);
                }

                try {
                    statement.executeBatch();
                } catch (BatchUpdateException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    @Override
    public void stop() throws Exception {
        this.factory.stop();
    }

    /*@Override
    public void createUser(UUID uuid, String id) throws SQLException {
        try (Connection connection = this.factory.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(this.processor.apply(minecraft_player_insert))) {
                statement.setString(1, uuid.toString());
                statement.execute();
            }

            try (PreparedStatement statement = connection.prepareStatement(this.processor.apply(discord_link_insert))) {
                statement.setString(1, uuid.toString());
                statement.setString(2, id);
                statement.execute();
            }
        }
    }

    @Override
    public void removeUser(UUID uuid) throws SQLException {
        try (Connection connection = this.factory.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(this.processor.apply(player_update))) {
                statement.setString(1, null);
                statement.setString(2, uuid.toString());
                statement.execute();
            }
        }
    }

    @Override
    public void removeUser(String id) throws SQLException {
        try (Connection connection = this.factory.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(this.processor.apply(discord_link_update))) {
                statement.setString(1, null);
                statement.setString(2, id);
                statement.execute();
            }
        }
    }

    @Override
    public String getIdentifier(String id) throws SQLException {
        try (PreparedStatement statement = this.factory.getConnection().prepareStatement(this.processor.apply(discord_link_select))) {
            statement.setString(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.getString("long");
            }
        }
    }

    @Override
    public String getPlayer(UUID uuid) throws SQLException {
        try (PreparedStatement statement = this.factory.getConnection().prepareStatement(this.processor.apply(player_select))) {
            statement.setString(1, uuid.toString());

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("uuid");
                }

                return null;
            }
        }
    }*/

    private boolean tableExists(Connection connection, String table) throws SQLException {
        try (ResultSet resultSet = connection.
                getMetaData().getTables(connection.getCatalog(), null, "%", null)) {
            while (resultSet.next()) {
                if (resultSet.getString(3).equalsIgnoreCase(table)) {
                    return true;
                }
            }
        }

        return false;
    }
}