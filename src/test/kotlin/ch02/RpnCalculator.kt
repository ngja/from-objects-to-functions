package ch02

object RpnCalculator {

    val operationMap = mapOf<String, (Double, Double) -> Double>(
        "+" to Double::plus,
        "-" to Double::minus,
        "*" to Double::times,
        "/" to Double::div,
    )

    val funStack = FunStack<Double>()

    fun calc(expr: String): Double {
        return expr.split(" ")
            .fold(funStack, ::reduce)
            .pop().first
    }

    fun reduce(stack: FunStack<Double>, token: String): FunStack<Double> {
        return if (operationMap.containsKey(token)) {
            val (right, tempStack) = stack.pop()
            val (left, resultStack) = tempStack.pop()
            resultStack.push(operation(token, left, right))
        } else {
            stack.push(token.toDouble())
        }
    }

    fun operation(token: String, left: Double, right: Double): Double {
        return operationMap[token]?.invoke(left, right) ?: error("$token not found")
    }
}