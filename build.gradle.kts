plugins {
    id("java")
    id("org.springframework.boot") version("2.7.4")
    id("io.spring.dependency-management") version ("1.0.14.RELEASE")
}

group = "com.yujiangjun"
version = "1.0-SNAPSHOT"

configurations{
    compileOnly{
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    maven{
        setUrl("https://maven.aliyun.com/repository/public/")
    }
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("io.minio:minio:8.4.4")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}