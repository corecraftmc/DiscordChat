package com.ryderbelserion.discordchat.discord.api

import com.ryderbelserion.discordchat.DiscordChat
import org.bukkit.plugin.java.JavaPlugin
import org.jetbrains.annotations.ApiStatus
import java.lang.reflect.Method

object ModuleRegistration {

    private val plugin: DiscordChat = JavaPlugin.getPlugin(DiscordChat::class.java)

    private var start: Method? = null
    private var stop: Method? = null

    init {
        try {
            this.start = ModuleProvider::class.java.getDeclaredMethod("start", ModulePlugin::class.java)
            this.start?.setAccessible(true)
            this.stop = ModuleProvider::class.java.getDeclaredMethod("stop")
            this.stop?.setAccessible(true)
        } catch (e: NoSuchMethodException) {
            throw ExceptionInInitializerError(e)
        }
    }

    @ApiStatus.Internal
    fun start(plugin: ModulePlugin?) {
        try {
            this.start?.invoke(null, plugin)
        } catch (exception: Exception) {
            this.plugin.logger.info("Failed to enable discord bot.")
            this.plugin.logger.info("Reason: " + exception.message)
        }
    }

    @ApiStatus.Internal
    fun stop() {
        try {
            this.stop?.invoke(null)
        } catch (exception: Exception) {
            this.plugin.logger.info("Failed to disable discord bot.")
            this.plugin.logger.info("Reason: " + exception.message)
        }
    }
}