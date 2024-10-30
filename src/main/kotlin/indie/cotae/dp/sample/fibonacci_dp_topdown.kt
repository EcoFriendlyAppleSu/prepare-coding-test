package indie.cotae.dp.sample

/**
 * o(n)의 방법으로 메모리를 사용해 Top Down 방식으로 구현
 * Top down 방식으로 구현 시, max depth를 염두해둬야 합니다.
 */

val fiboList = mutableListOf(1, 2)

fun main() {

    val n = readln().toInt()

    val result = fibonacciTopDown(n)
    println(result)
}

fun fibonacciTopDown(n: Int): Int {
    if (n == 0) {
        return fiboList[n]
    } else if (n == 1) {
        return fiboList[n]
    } else {
        val fiv = fibonacciTopDown(n - 1) + fibonacciTopDown(n - 2)
        fiboList.add(fiv)
        return fiv
    }
}
