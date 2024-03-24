package com.ryderbelserion.discordchat.platform.discord.api.embeds;

import com.ryderbelserion.discordchat.platform.utils.AvatarUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import org.bukkit.entity.Player;
import java.awt.Color;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

public class Embed {

    private final EmbedBuilder builder;
    private final EmbedField field;

    public Embed() {
        this.builder = new EmbedBuilder();
        this.field = new EmbedField(this.builder);
    }

    /**
     * Sets the title of the embed.
     *
     * @param text the text in the title.
     * @return the embed class with updated information.
     */
    public Embed title(String text) {
        this.builder.setTitle(text);

        return this;
    }

    /**
     * Sets the footer using text/icon
     *
     * @param text the text in the footer.
     * @param icon the icon in the footer.
     * @return the embed class with updated information.
     */
    public Embed footer(String text, String icon) {
        this.builder.setFooter(text, icon);

        return this;
    }

    /**
     * Sets the footer using the user object.
     *
     * @param user the user in the footer.
     * @return the embed class with updated information.
     */
    public Embed footer(User user) {
        this.builder.setFooter("Requested by: " + user.getAsMention(), user.getEffectiveAvatarUrl());

        return this;
    }

    /**
     * Sets the description of the embed.
     *
     * @param text the text to use.
     * @return the embed class with updated information.
     */
    public Embed description(String text) {
        this.builder.setDescription(text);

        return this;
    }

    /**
     * Sets the thumbnail using a url.
     *
     * @param url the url to use.
     * @return the embed class with updated information.
     */
    public Embed thumbnail(String url) {
        this.builder.setThumbnail(url);

        return this;
    }

    /**
     * Sets the thumbnail using a user object.
     *
     * @param user the user to use.
     * @return the embed class with updated information.
     */
    public Embed thumbnail(User user) {
        this.builder.setThumbnail(user.getEffectiveAvatarUrl());

        return this;
    }

    /**
     * Sets the embed image using a url.
     *
     * @param url the url to use.
     * @return the embed class with updated information.
     */
    public Embed image(String url) {
        this.builder.setImage(url);

        return this;
    }

    /**
     * Sets the embed image using a user object.
     *
     * @param user the user to use.
     * @return the embed class with updated information.
     */
    public Embed image(User user) {
        this.builder.setImage(user.getEffectiveAvatarUrl());

        return this;
    }

    /**
     * Sets the author using name/url
     *
     * @param name the name to use.
     * @param url the url to use.
     * @return the embed class with updated information.
     */
    public Embed author(String name, String url) {
        this.builder.setAuthor(name, null, url);

        return this;
    }

    /**
     * Sets the author using a user object.
     *
     * @param user the user to use.
     * @return the embed class with updated information.
     */
    public Embed author(User user) {
        this.builder.setAuthor(user.getEffectiveName(), null, user.getEffectiveAvatarUrl());

        return this;
    }

    public Embed author(Player player) {
        this.builder.setAuthor(player.getName(), null, AvatarUtils.avatar(player));

        return this;
    }

    /**
     * Sets the color of the embed.
     *
     * @param value the color to use.
     * @return the embed class with updated information.
     */
    public Embed color(String value) {
        this.builder.setColor(toColor(value));

        return this;
    }

    /**
     * Sets the timezone in the embed.
     *
     * @param timezone the timezone to use for embeds.
     * @return the embed class with updated information.
     */
    public Embed timestamp(String timezone) {
        this.builder.setTimestamp(LocalDateTime.now().atZone(ZoneId.of(timezone)));

        return this;
    }

    /**
     * Adds a field to an embed.
     *
     * @param field the field to add.
     * @return the embed class with updated information.
     */
    public Embed field(MessageEmbed.Field field) {
        this.field.field(field, field.isInline());

        return this;
    }

    /**
     * Add multiple fields to the embed.
     *
     * @param fields the list of fields to add.
     * @return the embed class with updated information.
     */
    public Embed fields(List<MessageEmbed.Field> fields) {
        fields.forEach(this::field);

        return this;
    }

    /**
     * @return the built embed.
     */
    public MessageEmbed build() {
        return this.builder.build();
    }

    /**
     * Converts hex colors to rgb
     *
     * @param text the text to convert.
     * @return the color object.
     */
    private Color toColor(String text) {
        int red = Integer.valueOf(text.substring(1, 3), 16);
        int blue = Integer.valueOf(text.substring(3, 5), 16);
        int green = Integer.valueOf(text.substring(5, 7), 16);

        return new Color(red, blue, green);
    }
}