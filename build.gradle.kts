import org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL

val junitVersion = "5.7.0-M1"

group = "jks"

plugins {
   kotlin("jvm") version "1.3.72"
}

repositories {
   mavenCentral()
}

dependencies {
   implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

   testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
   testImplementation("org.junit.jupiter:junit-jupiter-params:$junitVersion")
   testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}

tasks.withType<Test> {
   useJUnitPlatform()
   testLogging {
      events("passed", "skipped", "failed")
      exceptionFormat = FULL
   }
}

tasks.withType<Wrapper> {
   gradleVersion = "6.5.1"
}
