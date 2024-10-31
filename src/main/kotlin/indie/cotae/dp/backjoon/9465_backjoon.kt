package indie.cotae.dp.backjoon

import kotlin.math.max

fun main() {
    val n = readln().toInt()
    for (i in 0 until n) {
        val columnSize = readln().toInt()
        val board = mutableListOf<MutableList<Int>>()
        val result = Array(2) { IntArray(columnSize) { 0 } }


        for (k in 0 until 2) {
            val temp = readln().split(" ").map { it.toInt() }.toMutableList()
            board.add(temp)
        }

        result[0][0] = board[0][0]
        result[1][0] = board[1][0]
        if (columnSize == 1) {
            println(max(result[0][0], result[1][0]))
            continue
        }

        result[0][1] = board[1][0] + board[0][1]
        result[1][1] = board[0][0] + board[1][1]
        if (columnSize == 2) {
            println(max(result[0][1], result[1][1]))
            continue
        }

        for (j in 2 until columnSize) {
            result[0][j] = max(result[1][j-2], result[1][j-1]) + board[0][j]
            result[1][j] = max(result[0][j-2], result[0][j-1]) + board[1][j]
        }
        println(max(result[0][columnSize-1], result[1][columnSize-1]))
    }
}