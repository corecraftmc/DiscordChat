package com.ryderbelserion.discordchat.discord.api

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.entities.Guild

abstract class ModulePlugin {

    abstract fun onReady()

    abstract fun onStart()

    abstract fun onGuildReady(guild: Guild)

    abstract fun onStop()

    abstract fun token(): String

    abstract fun init(): Boolean

    fun enable() {
        ModuleRegistration.start(this)
    }

    fun disable() {
        ModuleRegistration.stop()
    }
}