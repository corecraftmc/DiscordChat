package com.ryderbelserion.discordchat.platform.impl.cache;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class CacheManager {

    private final Map<UUID, String> codes = new ConcurrentHashMap<>();

    public void addUser(UUID uuid, String code) {
        this.codes.putIfAbsent(uuid, code);
    }

    public void removeUser(UUID uuid) {
        this.codes.remove(uuid);
    }

    public String getCode(String code) {
        String foundKey = null;

        for (Map.Entry<UUID, String> keys : this.codes.entrySet()) {
            if (keys.getValue().equalsIgnoreCase(code)) {
                foundKey = keys.getValue();
                break;
            }
        }

        return foundKey;
    }

    public boolean hasUser(UUID uuid) {
        return this.codes.containsKey(uuid);
    }

    public UUID getIdentifier(String code) {
        UUID foundUUID = null;

        for (Map.Entry<UUID, String> keys : this.codes.entrySet()) {
            if (keys.getValue().equalsIgnoreCase(code)) {
                foundUUID = keys.getKey();
                break;
            }
        }

        return foundUUID;
    }

    public boolean isEmpty() {
        return this.codes.isEmpty();
    }
}