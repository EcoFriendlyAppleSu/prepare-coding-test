package indie.cotae.dp.backjoon

import kotlin.math.max

/**
 * 점화식 세울 때, 단계별로 구성해보고 해당 풀이가 시간, 공간 복잡도가 괜찮은지 판단 후에 적용하자
 */
fun main() {
    val n = readln().toInt()
    val board = readln().split(" ").map { it.toInt() }.toIntArray()
    val dp = board.copyOf().toMutableList()
    var maxNumber = Int.MIN_VALUE

    for (i in 0 until n) {
        for (j in 0 until i) {
            if (board[i] > board[j]) {
                dp[i] = max(dp[i], dp[j] + board[i])
            }
        }
        maxNumber = max(maxNumber, dp[i])
    }

    println(maxNumber)
}

/*
10
1 100 2 50 60 3 5 6 7 8
*/
