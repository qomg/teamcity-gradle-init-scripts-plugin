
import com.github.rodm.teamcity.TeamCityPluginExtension
import com.jfrog.bintray.gradle.BintrayExtension
import com.jfrog.bintray.gradle.RecordingCopyTask

plugins {
    id ("org.jetbrains.kotlin.jvm") version "1.1.3-2" apply false
    id ("com.github.rodm.teamcity-server") version "1.0" apply true
    id ("com.jfrog.bintray") version "1.7.3"
    id ("org.sonarqube") version "2.5"
}

group = "com.github.rodm"
version = "0.8-SNAPSHOT"

teamcity {
    version = "10.0"
}

bintray {
    user = findProperty("bintray.user") as String?
    key = findProperty("bintray.key") as String?

    filesSpec(closureOf<RecordingCopyTask> {
        from ("${project(":server").buildDir}/distributions")
        into ("gradle-init-scripts")
        include ("*.zip")
    })

    dryRun = false
    publish = true
    override = false

    pkg(closureOf<BintrayExtension.PackageConfig> {
        repo = "teamcity-plugins-generic"
        name = "gradle-init-scripts"
        desc = "A TeamCity plugin that provides support for reusing Gradle init scripts"
        websiteUrl = "https://github.com/rodm/teamcity-gradle-init-scripts-plugin"
        issueTrackerUrl = "https://github.com/rodm/teamcity-gradle-init-scripts-plugin/issues"
        vcsUrl = "https://github.com/rodm/teamcity-gradle-init-scripts-plugin.git"
        setLicenses("Apache-2.0")
        setLabels("teamcity", "plugin", "gradle", "scripts")

        version(closureOf<BintrayExtension.VersionConfig> {
            name = project.version as String
        })
    })
}

fun Project.teamcity(configuration: TeamCityPluginExtension.() -> Unit) {
    configure(configuration)
}

fun Project.bintray(configuration: BintrayExtension.() -> Unit) {
    configure(configuration)
}
