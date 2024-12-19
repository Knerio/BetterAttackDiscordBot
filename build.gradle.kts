plugins {
    id("java")
}

group = "eu.betterattack"
version = "1.0"

repositories {
    mavenCentral()
    maven("https://m2.dv8tion.net/releases")
}

dependencies {

    /** Annotations **/
    implementation("org.projectlombok:lombok:1.18.36")
    annotationProcessor("org.projectlombok:lombok:1.18.36")
    implementation("org.jetbrains:annotations:26.0.1")
    implementation("org.jetbrains:annotations:26.0.1")

    /** Rest API **/
    implementation("io.javalin:javalin:6.3.0")
    implementation("com.auth0:java-jwt:4.4.0")

    /** Jackson **/
    implementation("com.fasterxml.jackson.core:jackson-core:2.18.2")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.18.2")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.18.1")

    /** JDA **/
    implementation("net.dv8tion:JDA:5.2.0")

}