plugins {
	java
	id("org.springframework.boot") version "3.4.0-M2"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "com.codigomorsa"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(22)
	}
}

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/milestone") }
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.slf4j:slf4j-api:2.0.9")
	implementation("ch.qos.logback:logback-classic:1.4.11")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
