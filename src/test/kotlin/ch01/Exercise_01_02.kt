package ch01

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import kotlin.random.Random

class Exercise_01_02 {

    @Test
    fun `tdd sale`() {
        val milkPrice = Random.nextDouble(10.0)
        val breadPrice = Random.nextDouble(10.0)

        val foodPriceMap = mapOf(
            "milk" to milkPrice,
            "bread" to breadPrice,
        )
        val promotionMap = mapOf(
            "milk" to "3x2",
            "bread" to "8x6",
        )

        val cashRegister = CashRegister(foodPriceMap, promotionMap)

        expectThat(cashRegister.checkout(listOf("milk", "milk", "milk"))).isEqualTo(2.0)
        expectThat(cashRegister.checkout(listOf("bread", "milk", "milk", "milk", "bread", "bread"))).isEqualTo(2.0 + (breadPrice * 3))
        expectThat(cashRegister.checkout(listOf("bread", "milk", "milk", "milk", "bread", "bread", "bread", "bread", "bread", "bread", "bread"))).isEqualTo(8.0)
    }
}