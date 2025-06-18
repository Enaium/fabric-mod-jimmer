plugins {
    alias(libs.plugins.loom)
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