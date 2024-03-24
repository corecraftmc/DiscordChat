package com.ryderbelserion.discordchat.platform.discord.api.embeds;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

public class EmbedField {

    private final EmbedBuilder builder;

    public EmbedField(EmbedBuilder builder) {
        this.builder = builder;
    }

    /**
     * Adds a field using Strings.
     *
     * @param text the title of the field.
     * @param description the text for the field description.
     * @param inline whether the field should be inline.
     */
    public void field(String text, String description, boolean inline) {
        this.builder.addField(text, description, inline);
    }

    /**
     * Adds a field based on the field object.
     *
     * @param field the field object containing all the information we need.
     * @param inline whether the field should be inline.
     */
    public void field(MessageEmbed.Field field, boolean inline) {
        field(field.getName(), field.getValue(), inline);
    }

    /**
     * Adds a blank field.
     *
     * @param inline whether the field should be inline.
     */
    public void empty(boolean inline) {
        this.builder.addBlankField(inline);
    }
}