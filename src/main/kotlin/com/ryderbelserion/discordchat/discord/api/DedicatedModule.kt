package com.ryderbelserion.discordchat.discord.api

import com.ryderbelserion.discordchat.discord.api.command.CommandManager
import com.ryderbelserion.discordchat.discord.api.exceptions.ModuleInitializeException
import com.ryderbelserion.discordchat.discord.api.listeners.ListenerBuilder
import com.ryderbelserion.discordchat.discord.api.listeners.ModuleListener
import com.ryderbelserion.discordchat.discord.api.scheduler.Scheduler
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.requests.GatewayIntent
import net.dv8tion.jda.api.utils.cache.CacheFlag
import java.io.File

abstract class DedicatedModule(
    private val gateways: List<GatewayIntent> = emptyList(),
    private val cache: List<CacheFlag> = emptyList(),
    private val file: File,
    private val extra: DedicatedModule.() -> Unit = {}
) : ModulePlugin() {

    private var isActive: Boolean = false

    private var jda: JDA? = null

    fun start() {
        this.jda = JDABuilder.createDefault(token(), this.gateways).enableCache(this.cache).addEventListeners(getListener()).build()
    }

    fun get(): JDA? {
        return this.jda
    }

    private fun getListener(): ModuleListener {
        return ModuleListener(this, extra)
    }

    fun DedicatedModule.listeners(configuration: ListenerBuilder.() -> Unit): ListenerBuilder {
        return ListenerBuilder(this.jda).apply(configuration)
    }

    fun DedicatedModule.commands(guild: Guild, configuration: CommandManager.() -> Unit): CommandManager {
        return CommandManager().setJDA(this.jda).setGuild(guild).apply(configuration)
    }

    fun DedicatedModule.commands(configuration: CommandManager.() -> Unit): CommandManager {
        return CommandManager().setJDA(this.jda).apply(configuration)
    }

    override fun init(): Boolean {
        runCatching {
            start()

            if (!getDataFolder().exists()) getDataFolder().mkdir()

            if (!getAddonFolder().exists() && getAddonFolder().exists()) getAddonFolder().mkdir()

            Scheduler.start()

            onStart()
        }.onFailure {
            this.isActive = false

            throw ModuleInitializeException("Could not enable the dedicated module!")
        }.onSuccess {
            this.isActive = true
        }

        return isActive()
    }

    private fun isActive(): Boolean {
        return this.isActive
    }

    fun createGuildDir(id: Long, path: String) {
        val folder = getDataFolder().resolve(path)

        if (!folder.exists()) folder.mkdir()
        if (folder.exists() && !folder.resolve(id.toString()).exists()) folder.resolve(id.toString()).mkdir()
    }

    open fun getDataFolder() = this.file

    open fun getGuildFolder(path: String) = getDataFolder().resolve(path)

    open fun getGuildFolderID(path: String, id: Long) = getGuildFolder(path).resolve(id.toString())

    open fun getAddonFolder() = getDataFolder().resolve("addons")
}