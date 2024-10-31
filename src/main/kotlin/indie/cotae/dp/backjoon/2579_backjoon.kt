package indie.cotae.dp.backjoon

import kotlin.math.max

/**
 * 점화식을 세우는 것이 중요함.
 * 해결할 때 첫 번째 계단 처리를 어떻게 해야할 지 생각해내지 못했음. 2 번째 계단을 도달하는 가장 큰 값은 첫 번째 계단을 지나는 것임
 */
fun main() {
    val n = readln().toInt()
    val board = IntArray(n + 1) { 0 } // 계단의 가중치를 갖는 board
    for (i in 1 .. n) {
        board[i] = readln().toInt()
    }

    if (n == 1) {
        println(board[n])
        return
    }

    val resultBoard = IntArray(n + 1) // 각 계단에 도달하기 위한 최대값을 갖는 resultBoard

    resultBoard[1] = board[1]
    resultBoard[2] = board[1] + board[2]

    for (i in 3 .. n) {
        val a = resultBoard[i - 2] + board[i]
        val b = resultBoard[i - 3] + board[i -1] + board[i]
        resultBoard[i] = max(a, b)
    }

    println(resultBoard[n])
}