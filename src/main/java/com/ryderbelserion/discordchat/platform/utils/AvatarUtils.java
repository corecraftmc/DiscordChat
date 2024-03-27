package com.ryderbelserion.discordchat.platform.utils;

import org.bukkit.entity.Player;

public class AvatarUtils {

    public static String avatar(Player player) {
        String defaultUrl = "https://cravatar.eu/helmavatar/{uuid}/{size}.png";

        return defaultUrl
                .replace("{uuid}", player.getUniqueId().toString().replace("-", ""))
                .replace("{size}", "128");
    }
}