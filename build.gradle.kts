plugins {
    `java-library`
    `maven-publish`
    id("com.github.johnrengelman.shadow") version "8.1.0"
    id("net.minecrell.plugin-yml.bukkit") version "0.5.3"
}

repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://oss.sonatype.org/content/groups/public/")
    maven("https://eldonexus.de/repository/maven-public")
    maven("https://repo.maven.apache.org/maven2/")
}

val kotlinVersion = "1.8.20-RC"

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.19.4-R0.1-SNAPSHOT")
    implementation("de.eldoria.util:jackson-configuration:2.0.0-DEV")
    compileOnly("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlinVersion")
}
group = "dev.onetone"
version = "1.0-SNAPSHOT"
val shadebase = "${group}.varo.libs"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

tasks {
    register<Copy>("copyToServer") {
        val path = project.property("targetDir") ?: "C"
        if (path.toString().isEmpty()) {
            println("targetDir is not set in gradle properties")
            return@register
        }
        from(shadowJar)
        destinationDir = File(path.toString())
    }
    compileJava {
        options.encoding = "UTF-8"
    }

    compileTestJava {
        options.encoding = "UTF-8"
    }

    shadowJar {
        relocate("de.eldoria", shadebase + "eldoria")
        mergeServiceFiles()
    }

    test {
        useJUnitPlatform()
        testLogging {
            events("passed", "skipped", "failed")
        }
    }
    build {
        dependsOn(shadowJar)
    }
}

bukkit {
    name = "Varo"
    main = "dev.onetone.varo.Varo"
    apiVersion = "1.16"
    authors = listOf("hinterhältiger")


    commands{
        register("reloadborder"){
            description = "Reloads the minecraft border"
            permission = "op"
            permissionMessage = "§cYou don't have the permission to execute this command."
            aliases = listOf("vrb")
        }
        register("testvictory"){
            description = "Test victory"
            permission = "op"
            permissionMessage = "§cYou don't have the permission to execute this command."
            aliases = listOf("vtv")
        }
        register("setwinnerpodest"){
            description = "Sets the winner podest to the executors current coordinates"
            permission = "op"
            permissionMessage = "§cYou don't have the permission to execute this command."
            aliases = listOf("vswp")
        }
        register("jointeam"){
            description = "Joins a team"
            permission = "*"
            permissionMessage = "§cYou don't have the permission to execute this command."
            aliases = listOf("vjt")
        }
        register("getteam"){
            description = "Gets a given players team"
            permission = "op"
            permissionMessage = "§cYou don't have the permission to execute this command."
            aliases = listOf("vgt")
        }
    }
}