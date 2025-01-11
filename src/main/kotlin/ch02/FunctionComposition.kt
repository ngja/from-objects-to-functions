package ch02

// 연습문제 2.1 중위 함수 사용하여 선언적 스타일로 바꿔보기
typealias FUN<A, B> = (A) -> B
infix fun <A, B, C> FUN<A, B>.andThen(other: FUN<B, C>): FUN<A, C> = { a: A -> other(this(a)) }