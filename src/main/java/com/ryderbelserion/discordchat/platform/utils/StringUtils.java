package com.ryderbelserion.discordchat.platform.utils;

import java.util.List;

public class StringUtils {

    public static String convertList(List<String> list) {
        StringBuilder message = new StringBuilder();

        for (String line : list) {
            message.append(line).append("\n");
        }

        return message.toString();
    }
}