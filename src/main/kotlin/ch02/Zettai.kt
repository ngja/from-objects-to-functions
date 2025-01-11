package ch02

import org.http4k.core.*
import org.http4k.routing.bind
import org.http4k.routing.path
import org.http4k.routing.routes

data class Zettai(val lists: Map<User, List<TodoList>>): HttpHandler {
    val routes = routes(
        "/todo/{user}/{listName}" bind Method.GET to ::showList
    )

    override fun invoke(request: Request): Response = routes(request)

    fun showList(request: Request): Response = request
        .let(::extractListData)
        .let(::fetchListContent)
        .let(::renderHtml)
        .let(::createResponse)

    fun extractListData(request: Request): Pair<User, ListName> {
        val user = request.path("user").orEmpty()
        val listName = request.path("listName").orEmpty()
        return User(user) to ListName(listName)
    }

    fun fetchListContent(listId: Pair<User, ListName>): TodoList =
        lists[listId.first]
            ?.firstOrNull { it.listName == listId.second }
            ?: error("List unknown")

    fun renderHtml(todoList: TodoList): HtmlPage = HtmlPage("""
        <html>
        <body>
        <h1>Zettai</h1>
        <h2>${todoList.listName.name}</h2>
        <table>
            <tbody>${renderItems(todoList.items)}</tbody>
        </table>
        </body>
        </html>
    """.trimIndent())

    fun renderItems(items: List<TodoItem>) = items.map {
        """<tr><td>${it.description}</td></tr>""".trimIndent()
    }.joinToString("")

    fun createResponse(html: HtmlPage): Response = Response(Status.OK).body(html.raw)
}