plugins {
    alias(libs.plugins.run.paper)
    alias(libs.plugins.userdev)
    alias(libs.plugins.shadow)

    `java-library`
}

base {
    archivesName.set(rootProject.name)
}

val mcVersion = providers.gradleProperty("mcVersion").get()

repositories {
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")

    maven("https://repo.papermc.io/repository/maven-public/")

    maven("https://repo.triumphteam.dev/snapshots/")

    maven("https://maven.enginehub.org/repo/")

    mavenCentral()
}

dependencies {
    paperweight.paperDevBundle(libs.versions.bundle)

    compileOnly(libs.placeholder.api)

    compileOnly(libs.triumph.cmds)

    compileOnly(libs.discord.jda)

    compileOnly(libs.config.me)
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of("17"))
}

tasks {
    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(17)
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