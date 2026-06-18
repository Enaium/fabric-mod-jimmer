import me.modmuss50.mpp.PublishModTask

plugins {
    alias(libs.plugins.loom)
    alias(libs.plugins.publish)
    alias(libs.plugins.maven)
}

group = "cn.enaium"
version = "1.0.3+jimmer.${libs.versions.jimmer.get()}"

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

val includeAndExpose: Configuration by configurations.creating {
    exclude(module = "kotlin-stdlib")
    exclude(module = "kotlin-reflect")
}

//configurations {
//    include {
//        extendsFrom(includeAndExpose)
//    }
//    api {
//        extendsFrom(includeAndExpose)
//    }
//}

val publishing = gradle.startParameter.taskNames.any {
    it.split(":").any { split -> split.startsWith("publishToMaven") }
}

fun include(dependency: Any) {
    if (publishing) {
        return
    }
    dependencies.add("includeAndExpose", dependency)
}

dependencies {
    minecraft(libs.minecraft)
    implementation(libs.fabric.loader)
    include(libs.jackson)
    include(libs.jimmer)
}

tasks.processIncludeJars {
    from(includeAndExpose)
}

tasks.processResources {
    inputs.property("version", version)
    filesMatching("fabric.mod.json") {
        expand("version" to version)
    }
}

tasks.withType<PublishModTask>().configureEach {
    dependsOn(tasks.named("jar"))
}

publishMods {
    file = tasks.jar.get().archiveFile.get()
    type = STABLE
    displayName = "Fabric ORM Jimmer ${project.version}"
    changelog = ""
    modLoaders.add("fabric")

    curseforge {
        projectId = "1295848"
        accessToken = providers.gradleProperty("curseforge.token")
        minecraftVersionRange {
            start = "1.14"
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

mavenPublishing {

    publishToMavenCentral(automaticRelease = true)

    signAllPublications()

    coordinates(
        groupId = project.group.toString(),
        artifactId = project.name,
        version = project.version.toString()
    )

    pom {
        name = "Fabric ORM Jimmer"
        description = "Minecraft ORM Library"
        url = "https://github.com/Enaium/fabric-mod-jimmer"
        licenses {
            license {
                name = "The Apache License, Version 2.0"
                url = "http://www.apache.org/licenses/LICENSE-2.0.txt"
            }
        }
        developers {
            developer {
                name = "Enaium"
                url = "https://github.com/Enaium"
            }
        }
        scm {
            connection.set("scm:git:git://github.com/Enaium/fabric-mod-jimmer.git")
            developerConnection.set("scm:git:ssh://github.com/Enaium/fabric-mod-jimmer.git")
            url.set("https://github.com/Enaium/fabric-mod-jimmer")
        }
    }
}