package indie.abroad.week05

fun main() {
    val prices = intArrayOf(7,1,5,3,6,4)
    println(maxProfit2(prices))
}

// 시간 초과 발생 --> 2중 Loop를 지울 수 있어야 함.
fun maxProfit(prices: IntArray): Int {
    var result = Int.MIN_VALUE

    for (i in prices.indices) {
        for (j in i + 1 until prices.size) {
            if (prices[i] < prices[j]) {
                if (result < prices[j] - prices[i]) {
                    result = prices[j] - prices[i]
                }
            }
        }
    }
    if (result == Int.MIN_VALUE) return 0
    return result
}

fun maxProfit2(prices: IntArray): Int {
    var minValue = Int.MAX_VALUE
    var maxValue = 0

    for (price in prices) {
        if (price < minValue) {
            minValue = price
        } else if (price - minValue > maxValue) {
            maxValue = price - minValue
        }
    }
    return maxValue
}


/*
Input: prices = [7,1,5,3,6,4]
Output: 5
*/
