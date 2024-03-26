package com.ryderbelserion.discordchat.platform.impl.storage.interfaces;

public interface StorageImplementation {

    String getName();

    void init();

    void stop() throws Exception;

}