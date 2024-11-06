package indie.cotae.dp.backjoon

import kotlin.math.max
import kotlin.system.exitProcess

fun main() {
    val n = readln().toInt()
    val board = mutableListOf<MutableList<Int>>()
    val value = Array(n) { IntArray(n) { 0 } }

    for (i in 0 until n) {
        val temp = readln().split(" ").map { it.toInt() }.toMutableList()
        board.add(temp)
    }

    if (n == 1) {
        println(board[0][0])
        exitProcess(0)
    }

    value[0][0] = board[0][0]
    value[1][0] = board[1][0] + value[0][0]
    value[1][1] = board[1][1] + value[0][0]

    for (i in 2 until n) {
        for (j in 0 .. i) {
            if (j == 0) {
                value[i][j] = board[i][0] + value[i-1][0]
                continue
            } else if (j == i) {
                value[i][j] = board[i][j] + value[i - 1][j - 1]
                continue
            } else {
                value[i][j] = max((board[i][j] + value[i-1][j-1]), (board[i][j] + value[i-1][j]))
            }
        }
    }
    println(value[value.lastIndex].max())
}

/*
5
7
3 8
8 1 0
2 7 4 4
4 5 2 6 5
*/
