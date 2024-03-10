package com.rhizome.plugins

import com.rhizome.dto.NewRequestDto
import com.rhizome.dto.SuccessOutputDto
import com.rhizome.utils.Mailer
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json

fun Application.configureRouting() {
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
        })
    }

    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }
    }
    routing {
        post("/send-mail") {
            val requestData = call.receive<NewRequestDto>()
            Mailer.sendAnswerMail(requestData)
//            Mailer.sendInfoMail(requestData)

            call.respond(SuccessOutputDto())
        }
    }
}
