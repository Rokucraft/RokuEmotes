plugins {
    `java-library`
    id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
}

group = "com.rokucraft"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.4-R0.1-SNAPSHOT")
    compileOnly("com.elmakers.mine.bukkit:EffectLib:10.2")
    compileOnly("org.spongepowered:configurate-yaml:4.1.2")
    implementation("org.jspecify:jspecify:0.3.0")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}
bukkit {
    name = rootProject.name
    version = project.version.toString()
    main = "com.rokucraft.rokuemotes.RokuEmotesPlugin"
    apiVersion = "1.19"
    author = "Aikovdp"
    website = "https://rokucraft.com"
}
