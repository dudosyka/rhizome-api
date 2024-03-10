package com.rhizome.utils

import com.rhizome.conf.AppConf
import com.rhizome.dto.NewRequestDto
import net.axay.simplekotlinmail.delivery.mailerBuilder
import net.axay.simplekotlinmail.delivery.send
import net.axay.simplekotlinmail.email.emailBuilder
import org.simplejavamail.api.mailer.config.TransportStrategy

object Mailer {
    private val mailer = mailerBuilder(
        host = AppConf.mailer.host,
        port = AppConf.mailer.port,
        username = AppConf.mailer.username,
        password = AppConf.mailer.password
    ) {
        withTransportStrategy(TransportStrategy.SMTPS)
    }

    private val sendFrom = AppConf.mailer.username

    suspend fun sendAnswerMail(newRequestDto: NewRequestDto) {

        val email = emailBuilder {
            from("Rhizome", sendFrom)
            to(newRequestDto.email)

            withSubject("Мы получили вашу заявку")
            withHTMLText(TemplateParser.parse("answer.html") {
                replace("{{data.name}}", newRequestDto.name)
            })
        }

        email.send(mailer)
    }

    suspend fun sendInfoMail(newRequestDto: NewRequestDto) {
        val email = emailBuilder {
            from(sendFrom)
            to(sendFrom)

            withSubject("Новая заявка с сайта")
            withHTMLText(TemplateParser.parse("info.html") {
                replace("{{data.name}}", newRequestDto.name)
                replace("{{data.phone}}", newRequestDto.phone)
                replace("{{data.email}}", newRequestDto.email)
                replace("{{data.type}}", newRequestDto.type)
                replace("{{data.context}}", newRequestDto.context)
            })
        }

//        email.send(mailer)
    }
}