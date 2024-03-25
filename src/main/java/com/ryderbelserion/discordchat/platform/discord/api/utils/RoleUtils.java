package com.ryderbelserion.discordchat.platform.discord.api.utils;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;

public class RoleUtils {

    public static String getRoleName(Role role) {
        return role == null ? "" : role.getName();
    }

    public static Role getHighestRole(Member member) {
        return member.getRoles().isEmpty() ? null : member.getRoles().get(0);
    }

    public static Role getHighestRoleWithColor(Member member) {
        for (Role role : member.getRoles()) {
            if (role.getColor() == null) continue;

            return role;
        }

        return null;
    }
}