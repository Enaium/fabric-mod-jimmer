# Fabric ORM Jimmer

[![Version](https://img.shields.io/github/v/tag/Enaium/fabric-mod-jimmer?label=version&style=flat-square&logo=github)](https://github.com/Enaium/fabric-mod-jimmer/releases)
[![CurseForge Downloads](https://img.shields.io/curseforge/dt/1295848?style=flat-square&logo=curseforge)](https://www.curseforge.com/minecraft/mc-mods/fabric-orm-jimmer)
[![Modrinth Downloads](https://img.shields.io/modrinth/dt/ZyOeUjNc?style=flat-square&logo=modrinth)](https://modrinth.com/mod/fabric-mod-jimmer)

This is a mod that enables the use of the Jimmer ORM for Fabric mods.

Note: This does not add content! This is a dependency that other mods use for compatibility.

This mod works independently of the Minecraft version used, as it only requires Fabric Loader to be available.

## Installation Guide

### For Development (Mod Developers)

To use Fabric ORM Jimmer as a dependency in your Fabric mod:

1. Add the following to your `build.gradle` or `build.gradle.kts`:

**(Java Project) Groovy (build.gradle):**

```groovy
repositories {
    mavenCentral()
}
dependencies {
    implementation 'cn.enaium:fabric-orm-jimmer:<version>'
    annotationProcessor "org.babyfish.jimmer:jimmer-apt:<jimmer_version>"
}
```

**(Java Project) Kotlin DSL (build.gradle.kts):**

```kotlin
repositories {
    mavenCentral()
}
dependencies {
    implementation("cn.enaium:fabric-orm-jimmer:<version>")
    annotationProcessor("org.babyfish.jimmer:jimmer-apt:<jimmer_version>")
}
```

**(Kotlin Project) Kotlin DSL (build.gradle.kts):**

```groovy
plugins {
    kotlin("jvm") version "<kotlin_version>"
    id("com.google.devtools.ksp") version "<kotlin_version>+"
}

dependencies {
    implementation("cn.enaium:fabric-orm-jimmer:<version>")
    ksp("org.babyfish.jimmer:jimmer-ksp:<jimmer_version>")
}
```

Replace `<version>` with the latest version from the [releases page](https://github.com/Enaium/fabric-mod-jimmer/releases).

2. Refresh your dependencies and import as usual.

### For Players

1. Download the latest release from one of the following:
    - [CurseForge](https://www.curseforge.com/minecraft/mc-mods/fabric-orm-jimmer)
    - [Modrinth](https://modrinth.com/mod/fabric-orm-jimmer)
    - [GitHub Releases](https://github.com/Enaium/fabric-mod-jimmer/releases)

2. Place the downloaded `.jar` file into your Minecraft `mods` folder.

3. Make sure you have [Fabric Loader](https://fabricmc.net/use/) installed.

4. Launch Minecraft as usual.