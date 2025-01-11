package ch02

data class TodoList(val listName: ListName, val items: List<TodoItem>)
data class ListName(val name: String)
data class TodoItem(val description: String)
enum class TodoStatus { TODO, IN_PROGRESS, DONE, BLOCKED }
