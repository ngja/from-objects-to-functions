package com.hansol.ch01

import org.http4k.core.*
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.Jetty
import org.http4k.server.asServer

val htmlPage = """
    <html>
    <body>
    <h1 style="text-align:center; font-size:3em;">
    Hello Functional World!
    </h1>
    </body>
    </html>
""".trimIndent()

val app: HttpHandler = routes(
    "/greetings" bind Method.GET to ::greetings,
    "/data" bind Method.POST to ::receivedData,
)

fun greetings(req: Request): Response = Response(Status.OK).body(htmlPage)

fun receivedData(req: Request): Response = Response(Status.CREATED).body("Received: ${req.bodyString()}")

fun main() {
    app.asServer(Jetty(8080)).start()
}