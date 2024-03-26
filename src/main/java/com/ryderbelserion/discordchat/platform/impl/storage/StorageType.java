package com.ryderbelserion.discordchat.platform.impl.storage;

public enum StorageType {

    sqlite("sqlite"),
    mariadb("mariadb");

    private final String impl;

    StorageType(String impl) {
        this.impl = impl;
    }

    public String getImpl() {
        return this.impl;
    }
}