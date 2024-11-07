package indie.cotae.dp.backjoon

import kotlin.math.max

/**
 * dp 문제 접근할 때, 주어진 케이스를 따라가면서 손으로 전후 관계를 살펴 점화식을 찾는 연습 필요.
 */
fun main() {
    val n = readln().toInt()
    val board = readln().split(" ").map { it.toInt() }.toIntArray()

    for (i in 1 until n) {
        board[i] = max(board[i], board[i] + board[i-1])
    }
    println(board.max())
}

/*
10
10 -4 3 1 5 6 -35 12 21 -1
*/
