val ktorVersion: String by project
val kotlinVersion: String by project
val logbackVersion: String by project
val mailerVersion: String by project

plugins {
    kotlin("jvm") version "1.9.23"
    id("io.ktor.plugin") version "2.3.9"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.23"
}

group = "com.example"
version = "0.0.1"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/kotlin-js-wrappers") }
}

dependencies {
    fun ktor(part: String, module: String) = "io.ktor:ktor-$part-$module-jvm"
    fun ktorServer(module: String) = ktor(part = "server", module = module)
    fun ktorClient(module: String) = ktor(part = "client", module = module)
    fun jetBrains(module: String, version: String) = "org.jetbrains:$module:$version"
    fun kotlin(module: String) = jetBrains("kotlin:kotlin-$module", kotlinVersion)

    implementation(ktorServer("core"))
    implementation(ktorServer("auth"))
    implementation(ktorServer("auth-jwt"))
    implementation(ktorServer("host-common"))
    implementation(ktorServer("cors"))
    implementation(ktorServer("status-pages"))
    implementation(ktorServer("call-logging"))

    implementation(ktorServer("content-negotiation"))
    implementation(ktor("serialization", "kotlinx-json"))
    implementation(ktorServer("netty"))
    implementation(ktorServer("html-builder"))

    //Mailer
    implementation("net.axay:simplekotlinmail-core:$mailerVersion")
    implementation("net.axay:simplekotlinmail-client:$mailerVersion")
    implementation("net.axay:simplekotlinmail-html:$mailerVersion")

    implementation("ch.qos.logback:logback-classic:$logbackVersion")
}
