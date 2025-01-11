package ch02

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class Excercise_02_03 {

    @Test
    fun `a simple sum`() {
        expectThat(RpnCalculator.calc("4 5 +")).isEqualTo(9.0)
    }

    @Test
    fun `a double operation`() {
        expectThat(RpnCalculator.calc("3 2 1 - +")).isEqualTo(4.0)
    }

    @Test
    fun `a division`() {
        expectThat(RpnCalculator.calc("6 2 /")).isEqualTo(3.0)
    }

    @Test
    fun `a more complicated operaiton`() {
        expectThat(RpnCalculator.calc("6 2 1 + /")).isEqualTo(2.0)
        expectThat(RpnCalculator.calc("5 6 2 1 + / *")).isEqualTo(10.0)
    }

    @Test
    fun `a bit of everything`() {
        expectThat(RpnCalculator.calc("2 5 * 4 + 3 2 * 1 + /")).isEqualTo(2.0)
    }
}