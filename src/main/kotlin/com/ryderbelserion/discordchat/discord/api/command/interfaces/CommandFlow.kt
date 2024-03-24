package com.ryderbelserion.discordchat.discord.api.command.interfaces

import com.ryderbelserion.discordchat.discord.api.command.CommandEngine

abstract class CommandFlow {

    abstract fun addCommand(engine: CommandEngine)
    abstract fun addGuildCommand(engine: CommandEngine)

}