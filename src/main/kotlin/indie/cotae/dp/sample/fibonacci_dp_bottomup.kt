package indie.cotae.dp.sample


/**
 * bottom up 방식으로 문제 해결. dp 문제를 해결할 때, 상위 문제를 하위 문제로 나눌 수 있어야하고 하위 문제의 답으로 상위 문제의 답을 도출할 수 있어야합니다.
 * 또한 하위 문제의 결과 값이 중복되어 표시될 때 memization을 사용해 문제를 해결할 수 있습니다.
 */
val fibonacciList = mutableListOf(1,2)

fun main() {
    val n = readln().toInt()

    for (i in 2 .. n) {
        val number = fibonacciList[i - 1] + fibonacciList[i - 2]
        fibonacciList.add(number)
    }
    println(fibonacciList[n])
}