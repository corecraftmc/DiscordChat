package com.ryderbelserion.discordchat.platform.discord;

import com.ryderbelserion.discordchat.listeners.PlayerChatEvent;
import com.ryderbelserion.discordchat.listeners.PlayerDamageEvent;
import com.ryderbelserion.discordchat.listeners.PlayerTrafficEvent;
import com.ryderbelserion.discordchat.platform.discord.api.AbstractPlugin;
import com.ryderbelserion.discordchat.platform.discord.api.commands.CommandHandler;
import com.ryderbelserion.discordchat.platform.discord.api.embeds.Embed;
import com.ryderbelserion.discordchat.platform.discord.api.listeners.ModuleListener;
import com.ryderbelserion.discordchat.platform.discord.commands.DiscordLinkCommand;
import com.ryderbelserion.discordchat.platform.discord.commands.DiscordUnLinkCommand;
import com.ryderbelserion.discordchat.platform.discord.listeners.DiscordChatListener;
import com.ryderbelserion.discordchat.platform.impl.Config;
import com.ryderbelserion.discordchat.platform.impl.Locale;
import com.ryderbelserion.discordchat.platform.impl.enums.Messages;
import com.ryderbelserion.discordchat.platform.utils.AvatarUtils;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
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
    private CommandHandler handler = null;

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

        this.handler = new CommandHandler();
        this.handler.setJDA(this.jda);

        register(new DiscordChatListener());

        register(new PlayerTrafficEvent());
        register(new PlayerDamageEvent());
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

        this.plugin.getServer().getOnlinePlayers().forEach(player -> sendDiscordMessage(
                player,
                Messages.player_quit_title.getMessage(player, "{username}", player.getName()),
                null,
                this.locale.getProperty(Locale.player_quit_color)
        ));

        if (this.config.getProperty(Config.send_shutdown)) {
            sendDiscordMessage(
                    Messages.server_shutdown.getDiscordMessage(),
                    this.locale.getProperty(Locale.server_shutdown_color)
            );
        }

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

        this.handler.setGuild(guild);

        List.of(
                new DiscordLinkCommand(),
                new DiscordUnLinkCommand()
        ).forEach(this::register);

        this.handler.addGuildCommand(new DiscordLinkCommand(), OptionType.STRING, "code", "Links your account to your minecraft account.");
        this.handler.addGuildCommand(new DiscordUnLinkCommand());

        if (this.config.getProperty(Config.send_startup)) {
            sendDiscordMessage(
                    Messages.server_started.getDiscordMessage(),
                    this.locale.getProperty(Locale.server_started_color)
            );
        }
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

    public void sendDiscordMessage(Player player, String title, String description, String color) {
        Embed embed = new Embed();

        embed.author(title, AvatarUtils.avatar(player));

        if (description != null) {
            embed.description(description);
        }

        color(color, embed);
    }

    public void sendDiscordMessage(String description, String color) {
        Embed embed = new Embed();

        embed.description(description);

        color(color, embed);
    }

    private void color(String color, Embed embed) {
        embed.color(color);

        MessageEmbed messageEmbed = embed.build();

        for (String id : channels()) {
            TextChannel channel = this.guild.getTextChannelById(id);

            if (channel == null) continue;

            channel.sendMessageEmbeds(messageEmbed).queue();
        }
    }

    public void sendMinecraftMessage(String message) {
        if (message.isEmpty() || message.isBlank()) return;

        this.plugin.getServer().broadcast(MiniMessage.miniMessage().deserialize(message), "discordchat.chat");
    }
}