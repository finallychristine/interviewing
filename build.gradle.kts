plugins {
    kotlin("jvm") version "2.3.20"
    kotlin("plugin.serialization") version "2.3.20"
}

group = "org.example"
version = "1.0-SNAPSHOT"

val mockitoAgent = configurations.create("mockitoAgent")

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.code.gson:gson:2.13.2")
    implementation("com.squareup.okhttp3:okhttp:5.3.2")


    ///// Testing

    testImplementation(kotlin("test"))

    // junit
    testImplementation(platform("org.junit:junit-bom:6.0.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // mockito
    val mockito = testImplementation("org.mockito:mockito-core:5.23.0")
    testImplementation("org.mockito.kotlin:mockito-kotlin:6.2.3")
    testImplementation("org.mockito:mockito-junit-jupiter:5.23.0")

    mockitoAgent(mockito.toString()) {
        isTransitive = false
    }

    // assertj
    testImplementation("org.assertj:assertj-core:3.27.7")

    // other goodies
    // rxjava
    implementation("io.reactivex.rxjava3:rxjava:3.1.12")

    // kotlin serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.10.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.10.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")

    // logging, opt-in if needed
    implementation("io.github.oshai:kotlin-logging-jvm:8.0.01")
    implementation("ch.qos.logback:logback-classic:1.5.32")
}

kotlin {
    jvmToolchain(25)
}

tasks.test {
    useJUnitPlatform()
    jvmArgs("-javaagent:${mockitoAgent.asPath}")
}