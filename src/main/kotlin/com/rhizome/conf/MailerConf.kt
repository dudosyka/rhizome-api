package com.rhizome.conf

data class MailerConf(
    val host: String,
    val port: Int,
    val username: String,
    val password: String
)
