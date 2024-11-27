plugins {
    java
    id("com.github.johnrengelman.shadow") version "8.1.1" // Укажите актуальную версию плагина
    id("org.springframework.boot") version "3.2.3"
    id("io.spring.dependency-management") version "1.1.4"
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.liquibase:liquibase-core")
    implementation("org.liquibase.ext:liquibase-mongodb:4.30.0")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.springframework.boot:spring-boot-starter-web")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
}

tasks {
    shadowJar {
        archiveBaseName.set("gradleHelloWorld")
        archiveVersion.set("0.1")
        archiveClassifier.set("")
        manifest {
            attributes(
                "Main-Class" to "ru.otus.App"
            )
        }
    }

    build {
        dependsOn(shadowJar)
    }
}