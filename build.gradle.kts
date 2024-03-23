plugins {
    alias(libs.plugins.run.paper)
    alias(libs.plugins.userdev)
    alias(libs.plugins.shadow)

    kotlin("jvm") version "1.9.23"
}

base {
    archivesName.set(rootProject.name)
}

val mcVersion = providers.gradleProperty("mcVersion").get()

repositories {
    maven("https://repo.papermc.io/repository/maven-public/")

    maven("https://repo.triumphteam.dev/snapshots/")

    maven("https://maven.enginehub.org/repo/")

    mavenCentral()
}

dependencies {
    paperweight.paperDevBundle(libs.versions.bundle)

    compileOnly(libs.jetbrains.kotlin)

    compileOnly(libs.triumph.cmds)

    compileOnly(libs.discord.jda)

    compileOnly(libs.config.me)
}

kotlin {
    jvmToolchain(17)
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "17"
            javaParameters = true
        }
    }

    javadoc {
        options.encoding = Charsets.UTF_8.name()
    }

    processResources {
        filteringCharset = Charsets.UTF_8.name()
    }

    val jarsDir = File("$rootDir/jars")

    assemble {
        doFirst {
            delete(jarsDir)

            jarsDir.mkdirs()
        }

        dependsOn(reobfJar)

        doLast {
            copy {
                from(project.layout.buildDirectory.file("libs/${rootProject.name}-${project.version}.jar"))
                into(jarsDir)
            }
        }
    }

    runServer {
        jvmArgs("-Dnet.kyori.ansi.colorLevel=truecolor")

        defaultCharacterEncoding = Charsets.UTF_8.name()

        minecraftVersion(mcVersion)
    }

    processResources {
        val props = mapOf(
            "name" to rootProject.name,
            "version" to rootProject.version,
            "group" to rootProject.group,
            "description" to rootProject.description,
            "apiVersion" to providers.gradleProperty("apiVersion").get()
        )

        filesMatching("paper-plugin.yml") {
            expand(props)
        }
    }
}