package com.rhizome.conf

import com.typesafe.config.ConfigFactory
import io.ktor.server.config.*

object AppConf {
    private val appConf: ApplicationConfig = HoconApplicationConfig(ConfigFactory.load().getConfig("applicationConfig"))
    private val mailerConf: ApplicationConfig = appConf.config("mailer")

    val mailer: MailerConf = MailerConf(
        host = mailerConf.property("host").getString(),
        port = mailerConf.property("port").getString().toInt(),
        username = mailerConf.property("username").getString(),
        password = mailerConf.property("password").getString(),
    )

    val templateLocationPath = appConf.property("template-location-path").getString()
}