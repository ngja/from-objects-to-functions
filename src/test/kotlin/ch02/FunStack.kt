package ch02

// 연습문제 2.2 push 시 elements 의 제일 뒤로 넣어서 구현한 stack
data class FunStack<T>(private val elements: List<T> = emptyList()) {

    fun push(element: T): FunStack<T> {
        return FunStack(elements + element)
    }

    fun size(): Int = elements.size

    fun pop(): Pair<T, FunStack<T>> {
        return elements.last() to FunStack(elements.dropLast(1))
    }
}