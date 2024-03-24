package com.ryderbelserion.discordchat.platform.discord;

import com.ryderbelserion.discordchat.listeners.PlayerChatEvent;
import com.ryderbelserion.discordchat.platform.discord.api.AbstractPlugin;
import com.ryderbelserion.discordchat.platform.discord.api.listeners.ModuleListener;
import com.ryderbelserion.discordchat.platform.impl.Config;
import com.ryderbelserion.discordchat.platform.impl.Locale;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.bukkit.event.Listener;
import java.util.List;

public class DiscordBot extends AbstractPlugin {

    private final List<GatewayIntent> intents = List.of(
            GatewayIntent.MESSAGE_CONTENT,
            GatewayIntent.GUILD_PRESENCES,
            GatewayIntent.GUILD_MESSAGE_REACTIONS
    );

    private final List<CacheFlag> flags = List.of(
            CacheFlag.ACTIVITY
    );

    private JDA jda = null;
    private Guild guild = null;

    @Override
    public void start() {
        if (this.jda != null) {
            this.plugin.getLogger().warning("The bot is already enabled!");

            return;
        }

        String token = this.config.getProperty(Config.bot_token);

        if (token.isBlank()) {
            this.plugin.getLogger().warning("Token cannot be blank or empty.");

            return;
        }

        this.jda = JDABuilder.createDefault(token).enableIntents(this.intents).enableCache(this.flags).addEventListeners(new ModuleListener(this)).build();

        register(new PlayerChatEvent());
    }

    @Override
    public void stop() {
        if (this.jda == null) {
            return;
        }

        if (this.guild == null) {
            return;
        }

        sendDiscordMessage(this.locale.getProperty(Locale.server_shutdown));

        this.jda.shutdown();
    }

    @Override
    public void guild(Guild guild) {
        this.guild = guild;

        // If the guild id doesn't match.
        if (!guild.getId().equalsIgnoreCase(this.config.getProperty(Config.guild_id))) {
            this.config.setProperty(Config.guild_id, guild.getId());

            this.config.save();
            this.config.reload();
        }

        sendDiscordMessage(this.locale.getProperty(Locale.server_started));
    }

    @Override
    public JDA jda() {
        return this.jda;
    }

    @Override
    public List<String> channels() {
        return this.config.getProperty(Config.channels);
    }

    @Override
    public Guild guild() {
        return this.guild;
    }

    @Override
    public void register(ListenerAdapter listener) {
        this.jda.addEventListener(listener);
    }

    @Override
    public void register(Listener listener) {
        this.plugin.getServer().getPluginManager().registerEvents(listener, this.plugin);
    }

    public void sendDiscordMessage(String message) {
        if (message.isEmpty() || message.isBlank()) return;

        for (String id : channels()) {
            TextChannel channel = this.guild.getTextChannelById(id);

            if (channel == null) continue;

            channel.sendMessage(message).queue();
        }
    }
}