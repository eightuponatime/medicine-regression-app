import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    id("org.jetbrains.kotlin.plugin.compose")
    id("org.jetbrains.compose")
}

repositories {
    mavenCentral()
    google()
}

sourceSets {
    main {
        kotlin.srcDirs("src/core", "src/ui", "src/shared")
    }
    test {
        kotlin.srcDirs("src/test")
    }
}

dependencies {
    // Note, if you develop a library, you should use compose.desktop.common.
    // compose.desktop.currentOs should be used in launcher-sourceSet
    // (in a separate module for demo project and in testMain).
    // With compose.desktop.common you will also lose @Preview functionality
    implementation(compose.desktop.currentOs)

    // lib for statistics
    // ols regression
    // stats (mean, median, percentiles)
    // durbin-watson test
    // jarque-bera test (residual norm.)
    // cov., corr
    implementation("org.apache.commons:commons-math3:3.6.1")

    // for time series
    // ADF test
    // base regression (analog for commons math to test)
    implementation("com.github.haifengl:smile-core:3.1.1")
    // build plot
    // acf/ pacf plots for residuals
    // scatter plots for regression
    implementation("com.github.haifengl:smile-plot:3.1.1")

    // to read table data
    // pandas analog
    // load csv, excel
    implementation("tech.tablesaw:tablesaw-core:0.43.1")
    // optional for csv
    // lighter that tablesaw but idk what's better need to test
    implementation("com.github.doyaaaaaken:kotlin-csv-jvm:1.9.2")

    // logs (logger.info {  })
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.5")
    implementation("ch.qos.logback:logback-classic:1.4.14")


    // Include the Test API
    testImplementation(compose.desktop.uiTestJUnit4)

    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.1")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "MedicineRegressionApp"
            packageVersion = "1.0.0"

            description = "Medical Data Regression Analysis Tool"
        }
    }
}

tasks.test {
    useJUnitPlatform()

    // settings to show "println" logs
    testLogging {
      events("passed", "skipped", "failed", "standardOut", "standardError")
      showStandardStreams = true
      exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
    }
}
