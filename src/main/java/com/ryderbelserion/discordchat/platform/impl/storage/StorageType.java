package com.ryderbelserion.discordchat.platform.impl.storage;

public enum StorageType {

    sqlite("sqlite"),
    mysql("mysql");

    private final String name;

    StorageType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}