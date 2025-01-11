package ch01

class CashRegister(
    private val foodPriceMap: Map<String, Double>,
    private val foodPromotionMap: Map<String, String>,
) {

    fun checkout(foods: List<String>): Double {
        val foodCountMap = mutableMapOf<String, Int>()
        var total = 0.0

        for (food in foods) {
            if (foodCountMap.containsKey(food)) {
                foodCountMap[food] = foodCountMap[food]!! + 1
            } else {
                foodCountMap[food] = 1
            }
        }

        for ((foodName, count) in foodCountMap) {
            if (foodPromotionMap.containsKey(foodName)) {
                val (foodCount, totalPrice) = foodPromotionMap[foodName]!!.split("x").map { it.toInt() }
                val a = count / foodCount
                val b = count % foodCount
                total += (a * totalPrice) + ((foodPriceMap[foodName] ?: 0.0) * b)
            } else {
                total += (foodPriceMap[foodName] ?: 0.0) * count
            }
        }
        return total
    }
}