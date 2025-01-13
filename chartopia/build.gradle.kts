import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.jetbrains.compose.compiler)
    alias(libs.plugins.dokka)
    id("maven-publish")
    id("signing")
}

android {
    namespace = ProjectConfiguration.Chartopia.namespace
    compileSdk = ProjectConfiguration.Chartopia.compileSDK

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        minSdk = ProjectConfiguration.Chartopia.minSDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }

        getByName("debug") {
        }
    }

    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = ProjectConfiguration.Compiler.javaCompatibility
        targetCompatibility = ProjectConfiguration.Compiler.javaCompatibility

        isCoreLibraryDesugaringEnabled = true
    }

    dependencies {
        coreLibraryDesugaring(libs.android.desugarjdklibs)
    }
}

kotlin {
    applyDefaultHierarchyTemplate()

    androidTarget {
        publishLibraryVariants("release")

        compilerOptions {
            jvmTarget.set(JvmTarget.fromTarget(ProjectConfiguration.Compiler.jvmTarget))
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "chartopia"
            isStatic = true
        }
    }

    sourceSets {

        commonMain.dependencies {
            implementation(libs.napier)
            implementation(libs.android.annotations)

            // Tweener
            implementation(libs.czan)
            implementation(libs.kmpkit)

            // Coroutines
            implementation(libs.kotlin.coroutines.core)

            // Compose
            implementation(compose.ui)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.materialIconsExtended)
            implementation(compose.runtime)
            implementation(compose.components.resources)
            implementation(libs.compose.multiplatform.material3)
        }

        androidMain.dependencies {
            // Coroutines
            implementation(libs.kotlin.coroutines.android)

            // Android
            implementation(libs.android.core)
        }

        iosMain.dependencies {

        }
    }
}

// region Publishing

// Dokka configuration
val dokkaOutputDir = rootProject.layout.buildDirectory.asFile.get().resolve("dokka")
tasks.dokkaHtml { outputDirectory.set(file(dokkaOutputDir)) }
val deleteDokkaOutputDir by tasks.register<Delete>("deleteDokkaOutputDirectory") { delete(dokkaOutputDir) }
val javadocJar = tasks.create<Jar>("javadocJar") {
    archiveClassifier.set("javadoc")
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    dependsOn(deleteDokkaOutputDir, tasks.dokkaHtml)
    from(dokkaOutputDir)
}

group = ProjectConfiguration.Chartopia.Maven.group
version = ProjectConfiguration.Chartopia.versionName

publishing {
    publications {
        publications.withType<MavenPublication> {
            artifact(javadocJar)

            pom {
                name.set(ProjectConfiguration.Chartopia.Maven.name)
                description.set(ProjectConfiguration.Chartopia.Maven.description)
                url.set(ProjectConfiguration.Chartopia.Maven.packageUrl)

                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }

                issueManagement {
                    system.set("GitHub Issues")
                    url.set("${ProjectConfiguration.Chartopia.Maven.packageUrl}/issues")
                }

                developers {
                    developer {
                        id.set(ProjectConfiguration.Chartopia.Maven.Developer.id)
                        name.set(ProjectConfiguration.Chartopia.Maven.Developer.name)
                        email.set(ProjectConfiguration.Chartopia.Maven.Developer.email)
                    }
                }

                scm {
                    connection.set("scm:git:git://${ProjectConfiguration.Chartopia.Maven.gitUrl}")
                    developerConnection.set("scm:git:ssh://${ProjectConfiguration.Chartopia.Maven.gitUrl}")
                    url.set(ProjectConfiguration.Chartopia.Maven.packageUrl)
                }
            }
        }
    }
}

signing {
    if (project.hasProperty("signing.gnupg.keyName")) {
        println("Signing lib...")
        useGpgCmd()
        sign(publishing.publications)
    }
}

// endregion Publishing
