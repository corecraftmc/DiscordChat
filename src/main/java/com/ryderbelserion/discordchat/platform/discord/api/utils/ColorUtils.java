package com.ryderbelserion.discordchat.platform.discord.api.utils;

import java.awt.Color;

public class ColorUtils {

    /**
     * Converts hex colors to rgb
     *
     * @param text the text to convert.
     * @return the color object.
     */
    public static Color toColor(String text) {
        int red = Integer.valueOf(text.substring(1, 3), 16);
        int blue = Integer.valueOf(text.substring(3, 5), 16);
        int green = Integer.valueOf(text.substring(5, 7), 16);

        return new Color(red, blue, green);
    }

    /**
     * Converts color to hex string.
     *
     * @param color the color to convert.
     * @return the hex color.
     */
    public static String toHex(Color color) {
        return String.format("%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }
}