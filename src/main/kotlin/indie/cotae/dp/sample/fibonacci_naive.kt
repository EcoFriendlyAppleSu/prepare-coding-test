package indie.cotae.dp.sample

/**
 * o^2 의 방법으로 해결한 피보나치 수열
 */
fun main() {
    val startTime = System.currentTimeMillis()
    val n = readln().toInt()
    println("startTime is $startTime.")
    println("result is ${fibonacciNaive(n)}")
    val endTime = System.currentTimeMillis()
    println("endTime is $endTime")
    println("total time is ${(endTime - startTime) / 1000}")
}

fun fibonacciNaive(n: Int): Int {
    if (n == 0) {
        return 0
    } else if (n == 1) {
        return 1
    } else {
        val fiv = fibonacciNaive(n - 1) + fibonacciNaive(n - 2)
        return fiv
    }
}
