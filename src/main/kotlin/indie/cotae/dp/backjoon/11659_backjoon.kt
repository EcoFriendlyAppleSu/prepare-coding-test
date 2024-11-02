package indie.cotae.dp.backjoon

/**
 * 가중치에 대한 접근은 맞았으나 n multiple m 의 조건을 생각하지 못함.
 */
fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val numberList = readln().split(" ").map { it.toInt() }.toList()
    val valueList = mutableListOf(0)
    var sum = 0

    for (i in 0 until n) {
        sum += numberList[i]
        valueList.add(sum)
    }

    for (i in 0 until m) {
        val (start, end) = readln().split(" ").map { it.toInt() }
        println(valueList[end] - valueList[start-1])
    }
}
/*
5 3
5 4 3 2 1
1 3
2 4
5 5
*/
