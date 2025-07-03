plugins {
    alias(libs.plugins.loom)
    alias(libs.plugins.publish)
}

group = "cn.enaium"
version = "1.0.0+jimmer.${libs.versions.jimmer.get()}"

val lib: Configuration by configurations.creating

repositories {
    mavenCentral()
}

dependencies {
    minecraft(libs.minecraft)
    mappings(libs.fabric.yarn) {
        artifact {
            classifier = "v2"
        }
    }
    modImplementation(libs.fabric.loader)
    lib(libs.jimmer) {
        exclude(module = "kotlin-stdlib")
        exclude(module = "kotlin-reflect")
    }
}

tasks.processIncludeJars {
    from(lib)
}

tasks.processResources {
    inputs.property("version", version)
    filesMatching("fabric.mod.json") {
        expand("version" to version)
    }
}



publishMods {
    file = tasks.remapJar.get().archiveFile.get()
    type = STABLE
    displayName = "Fabric ORM Jimmer ${project.version}"
    changelog = ""
    modLoaders.add("fabric")

    curseforge {
        projectId = "1295848"
        accessToken = providers.gradleProperty("curseforge.token")
        minecraftVersionRange {
            start = "1.0"
            end = "latest"
        }
        optional("fabric-language-kotlin")
    }

    modrinth {
        projectId = "ZyOeUjNc"
        accessToken = providers.gradleProperty("modrinth.token")
        minecraftVersionRange {
            start = "1.0"
            end = "latest"
        }
        optional("fabric-language-kotlin")
    }

    github {
        repository = "Enaium/fabric-mod-jimmer"
        accessToken = providers.gradleProperty("github.token")
        commitish = "master"
    }
}